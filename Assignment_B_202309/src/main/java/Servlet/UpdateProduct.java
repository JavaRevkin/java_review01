package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import dao.DaoS;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bean.Product;

@jakarta.servlet.annotation.WebServlet("/update")
public class UpdateProduct extends jakarta.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoS dao;
    private String errors = "";
    private String productCode;
    Product product = new Product();

    // コンストラクター: サーブレットを初期化し、データベース接続を確立します。
    public UpdateProduct() {
        super();
        try {
            dao = new DaoS();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // HTTP GET リクエストを処理するコントローラー メソッド。
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            productCode = req.getParameter("product_code");
            res.getWriter().append("Served at: ").append(req.getContextPath());
            RequestDispatcher view = req.getRequestDispatcher("updateProduct.jsp");
            
            // 'productCode' パラメータが null または空かどうかを確認します。
            if (productCode == null || productCode.equals("")) {
                errors = errors + "製品が見つかりませんでした。\n";
                req.setAttribute("errors", errors);
            } else {
            	// データベースから製品の詳細を取得します。
                product = dao.getProduct(productCode);
                req.setAttribute("product", product);
            }
            view.forward(req, res);
        } catch (SQLException e) {
            errors = errors + "SQL例外が見つかりました\n";
            req.setAttribute("errors", errors);
        } catch (NullPointerException e) {
            errors = errors + "ヌルポインタ例外発見\n";
            req.setAttribute("errors", errors);
        } catch (NumberFormatException e) {
            errors = errors + "番号フォーマットの例外が見つかった\n";
            req.setAttribute("errors", errors);
        } catch (ServletException e) {
            errors = errors + "サーブレット例外が見つかりました\n";
            req.setAttribute("errors", errors);
        } catch (IOException e) {
            errors = errors + "IO例外が見つかった\n";
            req.setAttribute("errors", errors);
        }
    } 
    
    // HTTP POST リクエストを処理するコントローラー メソッド。
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// HTTP POST リクエスト パラメータからデータを取得します。
    	String productName = req.getParameter("productname");
        String price = req.getParameter("productprice");
        if(productName.length()>50) {
        	 RequestDispatcher view = req.getRequestDispatcher("updateProduct.jsp");
             errors = "製品名は 50 文字未満である必要があります。 \n";
             req.setAttribute("errors", errors);
             req.setAttribute("product", product);
             view.forward(req, res);
        }
        boolean isDigits = price.matches("\\d+");
        int productprice = 0;
        if(isDigits) {
			productprice = Integer.parseInt(price);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("updateProduct.jsp");
			errors = "金額を数値で入力してください \n";
			req.setAttribute("errors", errors);
			try {
				product = dao.getProduct(productCode);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            req.setAttribute("product", product);
			view.forward(req, res);
		}
        String deletefield = req.getParameter("deletefield");
       // int productprice = Integer.parseInt(price);
       // int code = Integer.parseInt(productCode);
        

        try {
        	productprice = Integer.parseInt(price);
        	int code = Integer.parseInt(productCode);
        	
        	// トランザクションを開始するには自動コミットを無効にします。
            dao.connection.setAutoCommit(false);

            // ユーザーが製品を削除したいかどうかを確認します。
            if (deletefield.equals("Delete")) {
                int checkIfExists = dao.deleteProduct(code);
                if (checkIfExists == 1) {
                	
                	// 削除に成功したら、商品がすでに販売されているかどうかを確認します。
                    if (dao.checkIfSold(code)) {
                        res.sendRedirect("search?alreadysold=1");
                    } else {
                    	// トランザクションをコミットし、検索ページにリダイレクトします。
                        dao.connection.commit();
                        res.sendRedirect("search");
                    }
                } else {
                    res.sendRedirect("search?notdeleted=1"); // 削除に失敗した場合は、エラー メッセージを表示して検索ページにリダイレクトします。
                }
            } else {
            	// 製品の更新時間がデータベースの更新時間と一致するかどうかを確認します。
                if (product.getUpdateDateTime().equals(dao.getUpdateTime(code))) {
                	// 製品の価格文字列から価格を抽出します。
                    String priceString = product.getPrice();
                    priceString = priceString.replaceAll("[^\\d.]", "");
                    // 商品名や価格が変更されていないか確認します。
                    if (!product.getProductName().equals(productName) || !(Integer.parseInt(priceString) == productprice)) {
                        int checkIfUpdates = dao.updateProduct(productName, productprice, code);
                        if (checkIfUpdates == 0) {
                            int checkIfUpdatesOnlyName = dao.updateProductNameOnly(productName, code);
                            dao.connection.commit();
                            if (Integer.parseInt(priceString) == productprice) {
                            	// 名前のみが更新された場合は、検索ページにリダイレクトします。
                                res.sendRedirect("search");
                           } else {
                                if (checkIfUpdatesOnlyName == 1) {
                                	// 名前の更新に失敗した場合はエラーでリダイレクトします。
                                    res.sendRedirect("search?savedonlyname=1");
                                } else {
                                	// 更新に失敗した場合はエラーでリダイレクトします。
                                    res.sendRedirect("search?deletedbeforeupdate=1");
                                }
                           }
                       } else {
                            dao.connection.commit();
                            res.sendRedirect("search");
                       }
                   } else {
                    	// 変更がない場合は検索ページにリダイレクトします。
                        res.sendRedirect("search");
                    }
              } else {
                	// 他の人が製品を編集した場合は、同時実行エラー メッセージを表示してリダイレクトします。
                    res.sendRedirect("search?concurrency=1");
                    System.out.println("Someone edited");
                }
            }
        } catch (IOException e) {
        	// IO 例外を処理し、エラー メッセージを表示します。
            try {
                dao.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            RequestDispatcher view = req.getRequestDispatcher("updateProduct.jsp");
            errors = errors + "製品名は 50 文字未満である必要があります。";
            req.setAttribute("errors", errors);
            view.forward(req, res);
        } catch (SQLException e) {
        	// SQL 例外を処理し、エラー メッセージを表示します。
            try {
                dao.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            RequestDispatcher view = req.getRequestDispatcher("updateProduct.jsp");
            errors = errors + "SQL例外が見つかりました\n";
            req.setAttribute("errors", errors);
            view.forward(req, res);
        } catch (NullPointerException e) {
        	// null ポインタ例外を処理し、エラー メッセージを表示します。
            try {
                dao.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            RequestDispatcher view = req.getRequestDispatcher("updateProduct.jsp");
            errors = errors + "ヌルポインタ例外発見\n";
            req.setAttribute("errors", errors);
            view.forward(req, res);
        } catch (NumberFormatException e) {
        	// 数値形式の例外を処理し、エラー メッセージを表示します。
            try {
                dao.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            RequestDispatcher view = req.getRequestDispatcher("updateProduct.jsp");
            errors = "番号フォーマットの例外が見つかった\n";
            req.setAttribute("errors", errors);
            view.forward(req, res);
        }
    }
}
