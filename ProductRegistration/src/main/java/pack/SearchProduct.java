package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pack.UpdateProduct.Product;

@WebServlet("/index")
public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GETメソッド
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 変数宣言
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// データ取得
		try {
			// 入力
			final String query = request.getParameter("query");
			request.setAttribute("Query", query != null ? query : "");
			// データベース接続
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASS);
			request.setAttribute("Success", true);
			// 検索結果取得
			if (query == null) stmt = conn.prepareStatement("SELECT product_code, product_name, price FROM m_product WHERE delete_datetime IS NULL ORDER BY product_code ASC;");
			else {
				stmt = conn.prepareStatement("SELECT product_code, product_name, price FROM m_product WHERE product_name LIKE ? AND delete_datetime IS NULL ORDER BY product_code ASC;");
				stmt.setString(1, "%" + query + "%");
			}
			rs = stmt.executeQuery();
			final List<Product> products = new ArrayList<>();
			while (rs.next()) products.add(new Product(rs.getString(1), rs.getString(2), rs.getInt(3)));
			request.setAttribute("Products", products);
		} catch (Exception e) {
			// エラー出力
			e.printStackTrace();
			request.setAttribute("Success", false);
		} finally {
			// クローズ
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// フォワード
		request.getRequestDispatcher("/view/index.jsp").forward(request, response);
	}

	/**
	 * POSTメソッド
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}