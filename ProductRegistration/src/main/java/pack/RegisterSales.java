package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/entry")
public class RegisterSales extends HttpServlet {
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
	@SuppressWarnings({"resource", "unchecked"})
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 変数宣言
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// データ取得
		try {
			// 入力
			final String action = request.getParameter("action"); // null, 追加, 登録
			final String code = request.getParameter("code"); // 商品コード
			final int quantity = StrOpe.isInteger(request.getParameter("quantity")) ? Integer.parseInt(request.getParameter("quantity")) : 0; // 数量
			// データベース接続
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASS);
			request.setAttribute("Success", true);
			final HttpSession session = request.getSession();
			// セッション初期値設定
			if (session.getAttribute("CurrentDate") == null) {
				session.setAttribute("CurrentDate", new Date(System.currentTimeMillis()));
				stmt = conn.prepareStatement("SELECT product_code, product_name, price FROM m_product WHERE delete_datetime IS NULL ORDER BY product_code ASC;");
				rs = stmt.executeQuery();
				final Map<String, String> pdNames = new HashMap<>();
				while (rs.next()) pdNames.put(rs.getString(1), rs.getString(2));
				session.setAttribute("ProductNames", pdNames);
				session.setAttribute("ProductQuantities", new HashMap<String, Integer>());
			}
			// 未登録確認
			stmt = conn.prepareStatement("SELECT EXISTS(SELECT * FROM t_sales WHERE sales_date = ?);");
			stmt.setDate(1, (Date)session.getAttribute("CurrentDate"));
			rs = stmt.executeQuery();
			rs.next();
			final boolean unregistered = !rs.getBoolean(1);
			request.setAttribute("Unregistered", unregistered);
			// 売上情報操作
			request.setAttribute("Completed", false);
			if (unregistered && action != null) {
				final Map<String, Integer> pdQuantities = (Map<String, Integer>)session.getAttribute("ProductQuantities");
				switch(action) {
				case "追加":
					// 売上情報追加
					pdQuantities.put(code, quantity);
					session.setAttribute("ProductQuantities", pdQuantities);
					break;
				case "削除":
					// 売上情報削除
					pdQuantities.remove(code);
					session.setAttribute("ProductQuantities", pdQuantities);
					break;
				case "登録":
					// 売上情報登録
					if (pdQuantities.size() > 0) {
						stmt = conn.prepareStatement("INSERT INTO t_sales VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);");
						stmt.setDate(1, (Date)session.getAttribute("CurrentDate"));
						for (Map.Entry<String, Integer> sales : pdQuantities.entrySet()) {
							stmt.setString(2, sales.getKey());
							stmt.setInt(3, sales.getValue());
							stmt.executeUpdate();
						}
						request.setAttribute("Completed", true);
					}
					break;
				default:
					request.setAttribute("Success", false);
					break;
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
		request.getRequestDispatcher("/view/entry.jsp").forward(request, response);
	}
}