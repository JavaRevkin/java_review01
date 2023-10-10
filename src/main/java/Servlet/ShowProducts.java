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
		
		try {
			String search = req.getParameter("search");
			String savedOnlyName = req.getParameter("savedonlyname");
			String concurrncy = req.getParameter("concurrncy");
			String notDeleted = req.getParameter("notdeleted");
			String deletedBeforeUpdate = req.getParameter("deletedbeforeupdate");
			String alreadySold = req.getParameter("alreadysold");
			
			if(savedOnlyName != null && savedOnlyName.equals("1")) {
				errors = "The price cannot be edited because it is on sale.\n";
				req.setAttribute("errors", errors);
			}
			
			if(concurrncy != null && concurrncy.equals("1")) {
				errors = "The value could not be updated because another user was updating the value at the same time.\n";
				req.setAttribute("errors", errors);
			}
			
			if(notDeleted != null && notDeleted.equals("1")) {
				errors = "Item has already been deleted by another user. \n";
				req.setAttribute("errors", errors);
			}
			
			if(deletedBeforeUpdate != null && deletedBeforeUpdate.equals("1")) {
				errors = "アップデート前にデータが削除されてしまった。\n";
				req.setAttribute("errors", errors);
			}
			
			if(alreadySold != null && alreadySold.equals("1")) {
				errors = "This product is already on sale and cannot be removed. \n";
				req.setAttribute("errors", errors);
			}
			
			res.getWriter().append("Served at: ").append(req.getContextPath());
			
			RequestDispatcher view = req.getRequestDispatcher("searchProducts.jsp");
			
			//検索ボックスが空の場合はすべての値が返され、それ以外の場合は検索値が返されます。
			
				if(search == null || search == "") {
					try {
						req.setAttribute("products", dao.getAllProducts());
					} catch (Exception e) {
						errors = errors + "SQL例外が見つかりました \n";
						req.setAttribute("errors", errors);
					}
				} else {
					if(dao.getSearchedProducts(search).size() == 0) {
						errors = errors + "No data was found.\n";
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
			} catch (NumberFormatException e) {
				errors = errors + "番号フォーマットの例外が見つかった \n";
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