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
 * 商品に関するServletの実装クラス
 */

@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * デフォルトコンストラクター
     */
	
	public ProductServlet() {
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
		
		request.setCharacterEncoding("UTF-8"); 	 // フォワード先の指定

		GetIndexPage(request, response); 		// フォワードの実行

	}

	private void GetIndexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// クエリ文字列が存在しない場合
		if (request.getQueryString() == null) {
			// 商品一覧を取得
			ProductDao productDao = new ProductDao();
			ArrayList<Product> productList = productDao.getAllProduct();
			request.setAttribute("product_list", productList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/IndexProduct.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			// 検索パラメータを取得
			String searchParams = request.getParameter("search-params");
			if (searchParams.length() > 50) {
				request.setAttribute("error", "製品名は 50 文字未満にする必要があります。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/IndexProduct.jsp");
				dispatcher.forward(request, response);
				return;
			} else {
				// 商品を検索
				ProductDao productDao = new ProductDao();
				ArrayList<Product> productList = productDao.searchProduct(searchParams);
				if (productList.isEmpty()) {
					request.setAttribute("error", "商品が見つかりません。");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/IndexProduct.jsp");
					dispatcher.forward(request, response);
					return;
				}
				else {
					request.setAttribute("product_list", productList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/IndexProduct.jsp");
					dispatcher.forward(request, response);
					return;
				}
			
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
