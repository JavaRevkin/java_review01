package Servlet;
import java.io.IOException;
import java.sql.SQLException;

import dao.DaoS;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//これがURLです
@jakarta.servlet.annotation.WebServlet("/search")
public class ShowProducts extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoS dao;
	public String errors = "";
	
	//このメソッドはデータベースを呼び出し、製品のリストを取得しようとします。
	public ShowProducts() {
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
		
		//このコードは、検索パラメータが渡されたかどうかを確認します。
		//渡された場合、検索結果を返します。
		//渡されなかった場合、すべての製品を返します。
		try {
			String search = req.getParameter("search");
			String savedOnlyName = req.getParameter("savedonlyname");
			String concurrency = req.getParameter("concurrency");
			String notDeleted = req.getParameter("notdeleted");
			String deletedBeforeUpdate = req.getParameter("deletedbeforeupdate");
			String alreadySold = req.getParameter("alreadysold");
			req.setAttribute("search", search);
			
			// 'savedOnlyName' パラメータが設定されており、「1」に等しいかどうかを確認します。
			if(savedOnlyName != null && savedOnlyName.equals("1")) {
				errors = "セール中のため価格は編集できません。.\n";
				req.setAttribute("errors", errors);
			}
			
			// 「同時実行」パラメータが設定されており、「1」に等しいかどうかを確認します。
			if(concurrency != null && concurrency.equals("1")) {
				errors = "別のユーザーが同時に値を更新しているため、値を更新できませんでした。\n";
				req.setAttribute("errors", errors);
			}
			
			// Check if 'notDeleted' parameter is set and equals "1".
			if(notDeleted != null && notDeleted.equals("1")) {
				//errors = "アイテムはすでに別のユーザーによって削除されています。 \n";
				errors = "この商品は既に販売済みのため削除できません。 \n";
				req.setAttribute("errors", errors);
			}
			
			// 「deletedBeforeUpdate」パラメータが設定されており、「1」に等しいかどうかを確認します。
			if(deletedBeforeUpdate != null && deletedBeforeUpdate.equals("1")) {
				errors = "アップデート前にデータが削除されてしまった。\n";
				req.setAttribute("errors", errors);
			}
			
			// 「alreadySold」パラメータが設定されており、「1」に等しいかどうかを確認します。
			if(alreadySold != null && alreadySold.equals("1")) {
				errors = "この商品はすでに販売中です。 \n";
				req.setAttribute("errors", errors);
			}
			
			res.getWriter().append("Served at: ").append(req.getContextPath());
			
			RequestDispatcher view = req.getRequestDispatcher("searchProducts.jsp"); //このコードは、ビュー JSP ファイルにリダイレクトします。
			
			//検索ボックスが空の場合はすべての値が返され、それ以外の場合は検索値が返されます。
			if(search == null || search == "") {
				try {
					req.setAttribute("products", dao.getAllProducts());
				} catch (SQLException e) {
					errors = "データベースから製品情報を取得できませんでした。エラーが発生しました。 \n";
					req.setAttribute("errors", errors);
				} catch (NumberFormatException e) {
					errors = errors + "番号フォーマットの例外が見つかった \n";
					req.setAttribute("errors", errors);
				}
			} else {
				// 検索文字列が空でない場合、検索結果を返します。
				if(dao.getSearchedProducts(search).size() == 0) {
					errors = "データが見つかりませんでした!! \n";
					req.setAttribute("errors", errors);
					req.setAttribute("products", dao.getAllProducts());
				}
				else {
					req.setAttribute("products", dao.getSearchedProducts(search));
				}
			}
				view.forward(req, res);
			} catch (SQLException e) {
				errors = errors + "SQL例外が見つかりました \n";
				req.setAttribute("errors", errors);
			} catch (NullPointerException e) {
				errors = errors + "ヌルポインタ例外発見 \n";
				req.setAttribute("errors", errors);
			} catch (ServletException e) {
				errors = errors + "サーブレット例外が見つかりました \n";
				req.setAttribute("errors", errors);
			} catch (IOException e) {
				errors = errors + "IO 例外が見つかった \n";
				req.setAttribute("errors", errors);
			} 
		
	}
}