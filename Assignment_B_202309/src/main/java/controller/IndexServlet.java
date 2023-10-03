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
@WebServlet("/")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * デフォルトコンストラクター
     */
	
	public IndexServlet() {
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/Index.jsp"); 		// フォワード先の指定
		dispatcher.forward(request, response); 				// フォワードの実行

	}


    /**
     * HTTP POST リクエストを処理します。このメソッドは空です。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     * @throws ServletException サーブレット固有のエラーが発生した場合。
     * @throws IOException      I/O エラーが発生した場合。
     */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
