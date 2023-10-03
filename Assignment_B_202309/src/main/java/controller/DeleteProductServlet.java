package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;

import java.io.IOException;
import java.util.ArrayList;

import dao.ProductDao;
import dao.SalesDao;

/**
 * Servletの実装クラス
 */

@WebServlet("/ProductDelete")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * デフォルトコンストラクター
     */
	public DeleteProductServlet() {
	}

	/**
     * HTTP GET リクエストを処理します。このメソッドは使用されません。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     * @throws ServletException サーブレット固有のエラーが発生した場合。
     * @throws IOException      I/O エラーが発生した場合。
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 自動生成メソッドスタブ

	}

    /**
     * HTTP POST リクエストを処理します。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     * @throws ServletException サーブレット固有のエラーが発生した場合。
     * @throws IOException      I/O エラーが発生した場合。
     */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 自動生成メソッドスタブ
		// リクエストの文字エンコーディングをUTF-8に設定
		request.setCharacterEncoding("UTF-8");

		if (!request.getParameter("id").isEmpty()) {
			
			// リクエストから製品IDを取得
			int id = Integer.parseInt(request.getParameter("id"));
			// ProductDao および SalesDao インスタンスを作成
			ProductDao productDao = new ProductDao();
			//SalesDao saleDao = new SalesDao();
			//Boolean isSold = saleDao.searchForSale(id); 	// 製品が売られているかどうかを確認
			 // 製品が売られていない場合、製品を削除
			//if (!isSold) {
				Boolean statusBoolean = productDao.deleteOne(id); 
				if (statusBoolean) {
					// 削除成功時の処理
					HttpSession session = request.getSession(false);
					//session.setAttribute("success", "製品が正常に削除されました。");
					response.sendRedirect("/Assignment_B_202309/Product");

				} else {
					// 削除失敗時のエラーメッセージを設定し、エラーページにフォワード
					request.setAttribute("error", "商品を削除できませんでした。");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/EditProduct.jsp");
					dispatcher.forward(request, response);
				}
			//}
			/*else {
				// 製品が売られている場合、エラーメッセージを設定し、エラーページにフォワード
				request.setAttribute("error", "商品を削除できませんでした。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/EditProduct.jsp");
				dispatcher.forward(request, response);
			}*/
			
		}

	}

}
