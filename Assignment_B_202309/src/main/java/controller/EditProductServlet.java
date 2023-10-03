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
@WebServlet("/EditProduct")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * デフォルトコンストラクター
     */
	
	public EditProductServlet() {
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
		// フォワード先の指定
		// フォワードの実行
		String[] url = request.getParameterValues("id");
		int id = Integer.parseInt(url[0]);
		Product product = new Product();
		ProductDao productDao = new ProductDao();
		product = productDao.findOne(id);
		SalesDao saleDao = new SalesDao();
		Boolean isSold = saleDao.searchForSale(id);
		request.setAttribute("product", product);
		request.setAttribute("isSold", isSold);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/EditProduct.jsp");
		dispatcher.forward(request, response);

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
		//// リクエストの文字エンコーディングをUTF-8に設定
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String currentUpdateTime = request.getParameter("current-update-time");
		Product currentProduct = new Product();
		ProductDao productDao = new ProductDao();
		currentProduct = productDao.findOne(Integer.parseInt(id));

		// セッションからメッセージを保存
		HttpSession session = request.getSession(false);
		if (!currentProduct.getUpdateDatetime().equals(currentUpdateTime)) {
			// エラーメッセージをセッションに設定
			// request.setAttribute("error", "商品名を入力してください。");
			request.getSession().setAttribute("error", "商品を更新できません。");
			response.sendRedirect("/Assignment_B_202309/Product");
			return;
		} else {
			if (name.isEmpty()) {
				//request.setAttribute("error", "商品名を入力してください。");
				// エラーメッセージをセッションに設定
				request.getSession().setAttribute("error", "商品名を入力してください。");
				response.sendRedirect("/Assignment_B_202309/Product");
				return;

			}
			if (name.length() > 50) {
				session.setAttribute("error", "製品名は 50 文字未満にする必要があります。");
				response.sendRedirect("/Assignment_B_202309/Product");
				return;

			}
			if (price.isEmpty()) {
				request.setAttribute("error", "商品価格を入力してください。");
				response.sendRedirect("/Assignment_B_202309/Product");
				return;
			} else {
				int productCode = Integer.parseInt(id);
				Product product = new Product();
				product.setProductCode(productCode);
				product.setProductName(name);
				product.setPrice(Integer.parseInt(price));
				productDao.updateOne(product);
				request.getSession().setAttribute("success", "製品が正常に更新されました。");
				response.sendRedirect("/Assignment_B_202309/Product");
				return;
			}
		}

	}

}
