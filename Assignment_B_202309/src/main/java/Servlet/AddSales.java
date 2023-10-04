package Servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.DaoS;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bean.Sales;
import java.time.format.DateTimeFormatter;


//これがURLです
@jakarta.servlet.annotation.WebServlet("/addsales")
public class AddSales extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoS dao;
	public String errors = "";
	List<Sales> sales = new ArrayList<Sales>();
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	String formattedDate = LocalDate.now().format(date);
	
	//このメソッドはデータベースを呼び出し、製品のリストを取得しようとします。
	public AddSales() {
        super();
        
        try {
			dao = new DaoS();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	

	//これは取得リクエストを処理するコントローラーです
	/**
	* @param request これはクライアントから送信された http リクエストです。
	* @param response これはサーバーから送信された http 応答です
	* @return このメソッドはビュー JSP ファイルに値を返します。
	*/
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			
			res.getWriter().append("Served at: ").append(req.getContextPath());
			//これは、取得リクエストのすべての属性とデータを設定します。
			RequestDispatcher view = req.getRequestDispatcher("addSales.jsp");
			req.setAttribute("products", dao.getAllProducts());
			req.setAttribute("todayssales", dao.getSales(formattedDate));
			req.setAttribute("todaysdate", formattedDate);
			req.setAttribute("sales", sales);
			view.forward(req, res);
			
			} catch (NullPointerException e) {
				errors = errors + "ヌルポインタ例外発見 \n";
				req.setAttribute("errors", errors);
			} catch (NumberFormatException e) {
				errors = errors + "番号フォーマットの例外が見つかった \n";
				req.setAttribute("errors", errors);
			} catch (ServletException e) {
				errors = errors + "サーブレット例外が見つかりました \n";
				req.setAttribute("errors", errors);
			} catch (IOException e) {
				errors = errors + "IO 例外が見つかった \n";
				req.setAttribute("errors", errors);
			} catch (SQLException e) {
				errors = errors + "SQL例外が見つかりました \n";
				req.setAttribute("errors", errors);
			}
		
	}
	
	/**
	  * POSTリクエストを処理します。
	  *
	  * @param req クライアントからの HTTP リクエスト。
	  * @param res サーバーからの HTTP 応答。
	  * @throws ServletException サーブレット固有のエラーが発生した場合。
	  * @throws IOException リクエストの処理中に I/O エラーが発生した場合。
	  */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//これは、ポストリクエストのすべての属性とデータを設定します。
		String productCode = req.getParameter("productcode");
		String productQuantity = req.getParameter("productquantity");
		String savefield = req.getParameter("savefield");
		
		//this is for saving the value
		if(savefield != null && savefield.equals("Save")) {
			errors = "";
			for (Sales sale : sales) {
		         try {
		        	 //データを挿入できるか、数量を更新する必要があるかをチェックする。
					boolean found = dao.addSales(sale.getSalesDate(), Integer.parseInt(sale.getProductCode()), sale.getQuantity());
					if(found == false) {
						errors = "この商品は商品テーブルから削除されました。";
						req.setAttribute("errors", errors);
					}
				} catch (NumberFormatException e) {
					errors = errors + "番号フォーマットの例外が見つかった \n";
					req.setAttribute("errors", errors);
				} catch (SQLException e) {
					try {
						//これは数量を更新する。
						dao.updateSales(sale.getSalesDate(), Integer.parseInt(sale.getProductCode()), sale.getQuantity());
					} catch (NumberFormatException e1) {
						errors = errors + "番号フォーマットの例外が見つかった \n";
						req.setAttribute("errors", errors);
					} catch (SQLException e1) {
						errors = errors + "SQL例外が見つかりました \n";
						req.setAttribute("errors", errors);
					}
					
				}
		      }
			sales.clear();
		}
		
		//ローカルリストにデータを追加する
		Sales sale = new Sales();
		sale.setSalesDate(formattedDate.toString()); // 販売日を現在の日付に設定します。
		
		// productCode と productQuantity の両方が null でないかどうかを確認します。
		if(productCode != null && productQuantity != null) {
			String[] separate = productCode.split(" ");
			try {
				// 指定されたコードを持つ製品が削除されているかどうかを確認します。
				if(dao.checkIfDeleted(Integer.parseInt(separate[0]))) {
					errors = errors + "この商品は削除されました。\n";
					req.setAttribute("errors", errors);
				} else {
					if(sales.size()==0) {
						// 販売リストが空の場合は、販売エントリを直接追加します。
						sale.setProductCode(Integer.parseInt(separate[0]));
						sale.setProductName(separate[1]);
						int length= productQuantity.length();
						if(length>=10) {
							errors = "少量の数量を入力してください。 \n";
							req.setAttribute("errors", errors);
						} else {
							sale.setQuantity(Integer.parseInt(productQuantity));
							sales.add(sale);
						}
					}
					else {
						// 販売リストが空でない場合は、数量の更新を処理します。
						sale.setProductCode(Integer.parseInt(separate[0]));
						sale.setProductName(separate[1]);
							for(int i=0; i<sales.size(); i++) {
								if(Integer.parseInt(sales.get(i).getProductCode())==Integer.parseInt(separate[0])) {
									if(productQuantity.length()>=10) {
										sale.setQuantity(999999999); // 製品がすでに販売リストに存在する場合は、数量を更新します。
										sales.remove(i);
										errors = "少量の数量を入力してください。 \n";
										req.setAttribute("errors", errors);
									} else {
										if((Integer.parseInt(productQuantity)+sales.get(i).getQuantity())>=999999999) {
											sale.setQuantity(999999999);
											sales.remove(i);
										} else {
											sale.setQuantity(Integer.parseInt(productQuantity)+sales.get(i).getQuantity());
											sales.remove(i);
										}
										 // 製品がすでに販売リストに存在する場合は、数量を更新します。
										//sales.remove(i);
									}
									
								} else {
									// 現在のリストに製品が見つからない場合は、数量を設定します。
									sale.setQuantity(Integer.parseInt(productQuantity));
									
								}
								
							}
							sales.add(sale);
					}
					
				}
			} catch (NumberFormatException e) {
				errors = "有効な数量を入力してください。 \n";
				req.setAttribute("errors", errors);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		// 「addSales.jsp」ビューの RequestDispatcher を取得します。
		RequestDispatcher view = req.getRequestDispatcher("addSales.jsp");
		//this are local data
		req.setAttribute("todaysdate", formattedDate);
		req.setAttribute("sales", sales);
		
		try {
			// データベースからデータを取得し、リクエスト属性として設定します。
			//req.setAttribute("todayssales", dao.getSales(date.toString()));
			req.setAttribute("todayssales", dao.getSales(formattedDate));
			req.setAttribute("products", dao.getAllProducts());
		} catch (SQLException e) {
			errors = errors + "SQL例外が見つかりました \n";
			req.setAttribute("errors", errors);
		}
		
		view.forward(req, res);
	}
}