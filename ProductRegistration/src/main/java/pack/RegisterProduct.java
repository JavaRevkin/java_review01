package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class RegisterProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GETメソッド
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * POSTメソッド
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 変数宣言
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// データ取得
		try {
			// 入力
			final String name = request.getParameter("name");
			final int price = StrOpe.isInteger(request.getParameter("price")) ? Integer.parseInt(request.getParameter("price")) : -1;
			request.setAttribute("ProductName", name);
			if (name != null && price > 0) {
				// データベース接続
				Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
				conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASS);
				request.setAttribute("Success", true);
				// 商品名重複確認
				stmt = conn.prepareStatement("SELECT EXISTS(SELECT * FROM m_product WHERE product_name = ?);");
				stmt.setString(1, name);
				rs = stmt.executeQuery();
				rs.next();
				final boolean isUniqueName = !rs.getBoolean(1);
				request.setAttribute("UniqueName", isUniqueName);
				if (isUniqueName) {
					// 商品コード取得
					stmt = conn.prepareStatement("SELECT MIN(product_code + 1) AS product_code FROM m_product WHERE (product_code + 1) NOT IN (SELECT product_code FROM m_product);");
					rs = stmt.executeQuery();
					rs.next();
					int code = rs.getInt(1);
					request.setAttribute("RangeCode", code < DBInfo.MAX_NUM);
					// 商品追加
					if (code < DBInfo.MAX_NUM) {
						stmt = conn.prepareStatement("INSERT INTO m_product VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL);");
						stmt.setString(1, new DecimalFormat("000").format(code));
						stmt.setString(2, name);
						stmt.setInt(3, price);
						stmt.executeUpdate();
					}
				}
			}
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
		request.getRequestDispatcher("/view/add.jsp").forward(request, response);
	}
}