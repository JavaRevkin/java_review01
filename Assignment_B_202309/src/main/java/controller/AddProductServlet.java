package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

import java.io.IOException;
import java.util.ArrayList;

import dao.ProductDao;


 /**
 * Servletの実装クラス
 */
 
@WebServlet("/AddProduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * デフォルトコンストラクター
	 */
	public AddProductServlet() {
	}

    /**
     * HTTP GET リクエストを処理します。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     * @throws ServletException サーブレット固有のエラーが発生した場合。
     * @throws IOException      I/O エラーが発生した場合。
     */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// リクエストを AddProduct.jsp ビューに転送します
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/AddProduct.jsp");				// フォワード先の指定
		dispatcher.forward(request, response);					// フォワードの実行

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
		// TODO Auto-generated method stub
		
		// リクエストの文字エンコーディングをUTF-8に設定
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		
		// 製品名が空であるかどうかを確認し、適切にエラーを処理します
		if (name.isEmpty()) {
			// エラーメッセージをリクエスト属性に設定
			request.setAttribute("error", "商品名を入力してください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/AddProduct.jsp");
			dispatcher.forward(request, response);
		}
		
		// 製品名が最大文字数を超えているかどうかを確認し、適切にエラーを処理します
		if (name.length() > 50) {
			// エラーメッセージをリクエスト属性に設定
			request.setAttribute("error", "製品名は 50 文字未満にする必要があります。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/AddProduct.jsp");
			dispatcher.forward(request, response);
		}
		
		// 商品価格が空であるかどうかを確認し、適切にエラーを処理します
		if (price.isEmpty()) {
			request.setAttribute("error", "商品価格を入力してください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/AddProduct.jsp");
			dispatcher.forward(request, response);
		} else {
			Product product = new Product(name, Integer.parseInt(price)); 				// 新しいProductオブジェクトを作成
			// ProductDaoインスタンスを生成してデータベースに挿入
			ProductDao productDao = new ProductDao();
			productDao.insertOne(product);
			response.sendRedirect("/Assignment_B_202309/Product");			// リダイレクトして別のページに移動
		}

	}

}
