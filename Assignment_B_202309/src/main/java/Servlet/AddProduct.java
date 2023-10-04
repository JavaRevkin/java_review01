package Servlet;
import java.io.IOException;
import java.sql.SQLException;

import dao.DaoS;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//これがURLです
@jakarta.servlet.annotation.WebServlet("/addproduct")
public class AddProduct extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoS dao;
	public String errors = "";
	
	//このメソッドはデータベースを呼び出し、製品のリストを取得しようとします。
	public AddProduct() {
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
		// RequestDispatcher を作成して、リクエストを「addProducts.jsp」JSP ページに転送します。
		RequestDispatcher view = req.getRequestDispatcher("addProducts.jsp");
		//req.setAttribute("products", dao.getAllProducts());
		
		view.forward(req, res);
		} catch (ServletException e) {
			// ServletException が発生した場合はそれを処理し、エラー メッセージを "errors" 属性に保存します。
			errors = errors + "サーブレット例外が見つかりました \n" ;
			req.setAttribute("errors", errors);
		} catch (IOException e) {
			// IOException が発生した場合はそれを処理し、エラー メッセージを "errors" 属性に保存します。
			errors = errors + "IO 例外が見つかった \n";
			req.setAttribute("errors", errors);
		}
	}
	
	/**
	 * これはPOSTリクエストを処理するコントローラーです。
	 *
	 * @param request  クライアントから送信されたHTTPリクエストです。
	 * @param response サーバーから送信されたHTTP応答です。
	 * @throws ServletException サーブレットの例外が発生した場合にスローされます。
	 * @throws IOException      入出力例外が発生した場合にスローされます。
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// リクエストパラメータから商品名と価格を取得します。
			
		
		try {
			String productName = req.getParameter("productname");
			String price = req.getParameter("productprice");
			boolean isDigits = price.matches("\\d+");
			int productprice = 0;
			if(isDigits) {
				productprice = Integer.parseInt(price);
			} else {
				RequestDispatcher view = req.getRequestDispatcher("addProducts.jsp");
				errors = "金額を入力してください \n";
				req.setAttribute("errors", errors);
				view.forward(req, res);
			}
			
			
			dao.connection.setAutoCommit(false);
			dao.addProduct(productName, productprice); // 指定された名前と価格の製品をデータベースに追加します。
			dao.connection.commit();
			res.sendRedirect("search"); // 追加が成功すると、「検索」ページにリダイレクトされます。
		} catch (IOException e) {
	       try {
	    	// IOException が発生した場合はトランザクションをロールバックします。
			dao.connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
	       // リクエストを「addProducts.jsp」ページに転送します
			RequestDispatcher view = req.getRequestDispatcher("addProducts.jsp");
			errors = errors + "IO 例外が見つかった \n";
			req.setAttribute("errors", errors);
			view.forward(req, res);
		} catch (SQLException e) {
			try {
			dao.connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
			// リクエストを「addProducts.jsp」ページに転送し、SQL 例外のエラー メッセージを設定します。
			RequestDispatcher view = req.getRequestDispatcher("addProducts.jsp");
			errors = errors + "SQL例外が見つかりました \n";
			req.setAttribute("errors", errors);
			//e.printStackTrace();
			view.forward(req, res);
		} catch (NullPointerException e) {
			
			try {
			dao.connection.rollback();
			RequestDispatcher view = req.getRequestDispatcher("addProducts.jsp");
			errors = errors + "金額を入力してください \n";
			req.setAttribute("errors", errors);
			view.forward(req, res);
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
			// リクエストを「addProducts.jsp」ページに転送し、NullPointerException のエラー メッセージを設定します。
			RequestDispatcher view = req.getRequestDispatcher("addProducts.jsp");
			errors = errors + "ヌルポインタ例外発見 \n";
			req.setAttribute("errors", errors);
			view.forward(req, res);
		} catch (NumberFormatException e) {
			try {
			dao.connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
			// リクエストを「addProducts.jsp」ページに転送し、NumberFormatException のエラー メッセージを設定します。
			RequestDispatcher view = req.getRequestDispatcher("addProducts.jsp");
			errors = errors + "番号フォーマットの例外が見つかった \n";
			req.setAttribute("errors", errors);
			view.forward(req, res);
		}
	}
}