package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/edit")
public class UpdateProduct extends HttpServlet {
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
			final String action = request.getParameter("action"); // null, 変更, 削除
			final String code = request.getParameter("code"); // 商品コード
			final String newName = request.getParameter("name"); // 新しい商品名
			final int newPrice = StrOpe.isInteger(request.getParameter("price")) ? Integer.parseInt(request.getParameter("price")) : 0; // 新しい単価
			// データベース接続
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASS);
			request.setAttribute("Success", true);
			final HttpSession session = request.getSession();
			// 商品有無確認
			stmt = conn.prepareStatement("SELECT EXISTS(SELECT * FROM m_product WHERE product_code = ? AND delete_datetime IS NULL);");
			stmt.setString(1, code);
			rs = stmt.executeQuery();
			rs.next();
			final boolean isInDatabase = rs.getBoolean(1);
			request.setAttribute("InDatabase", isInDatabase);
			// 商品情報編集
			if (isInDatabase) {
				// 商品情報取得
				stmt = conn.prepareStatement("SELECT product_name, price, update_datetime FROM m_product WHERE product_code = ?;");
				stmt.setString(1, code);
				rs = stmt.executeQuery();
				rs.next();
				request.setAttribute("Product", new Product(code, rs.getString(1), rs.getInt(2)));
				Timestamp lastConnUDTime = rs.getTimestamp(3);
				// 変更可能
				request.setAttribute("UniqueName", true);
				request.setAttribute("Modifiable", true);
				// 商品情報編集
				if (action == null) session.setAttribute("UpdateTime", lastConnUDTime); // 最初の接続時
				else if (action.equals("変更")) {
					// 商品名重複確認
					stmt = conn.prepareStatement("SELECT EXISTS(SELECT * FROM m_product WHERE product_code != ? AND product_name = ?);");
					stmt.setString(1, code);
					stmt.setString(2, newName);
					rs = stmt.executeQuery();
					rs.next();
					final boolean isUniqueName = !rs.getBoolean(1);
					request.setAttribute("UniqueName", isUniqueName);
					if (isUniqueName) {
						// 売上有無確認
						stmt = conn.prepareStatement("SELECT EXISTS(SELECT * FROM t_sales WHERE product_code = ?);");
						stmt.setString(1, code);
						rs = stmt.executeQuery();
						rs.next();
						final boolean hasSold = rs.getBoolean(1);
						// 単価変更確認
						boolean modifiable = true;
						if (hasSold) {
							stmt = conn.prepareStatement("SELECT price FROM m_product WHERE product_code = ?;");
							stmt.setString(1, code);
							rs = stmt.executeQuery();
							rs.next();
							modifiable = newPrice == rs.getInt(1);
						}
						request.setAttribute("Modifiable", modifiable);
						if (modifiable && newPrice > 0) {
							// 楽観的排他制御
							final boolean isSameTime = lastConnUDTime.equals((Timestamp)session.getAttribute("UpdateTime"));
							request.setAttribute("Controllable", isSameTime);
							// 商品情報変更
							if (isSameTime) {
								stmt = conn.prepareStatement("UPDATE m_product SET product_name = ?, price = ?, update_datetime = CURRENT_TIMESTAMP WHERE product_code = ?;");
								stmt.setString(1, newName);
								stmt.setInt(2, newPrice);
								stmt.setString(3, code);
								stmt.executeUpdate();
							}
							request.setAttribute("Product", null);
						}
					}
				} else if (action.equals("削除")) {
					// 楽観的排他制御
					final boolean isSameTime = lastConnUDTime.equals((Timestamp)session.getAttribute("UpdateTime"));
					request.setAttribute("Controllable", isSameTime);
					// 商品削除
					if (isSameTime) {
						stmt = conn.prepareStatement("UPDATE m_product SET update_datetime = CURRENT_TIMESTAMP, delete_datetime = CURRENT_TIMESTAMP WHERE product_code = ?;");
						stmt.setString(1, code);
						stmt.executeUpdate();
					}
					request.setAttribute("Product", null);
				} else request.setAttribute("Success", false);
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
		request.getRequestDispatcher("/view/edit.jsp").forward(request, response);
	}

	/**
	 * 商品情報レコード
	 */
	public static record Product(String code, String name, int price) {
	}
}