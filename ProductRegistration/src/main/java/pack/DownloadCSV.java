package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/output")
public class DownloadCSV extends HttpServlet {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 変数宣言
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// データ取得
		try {
			// 入力
			final String action = request.getParameter("action"); // null, 商品別売上集計CSV, 指定年月商品別売上集計CSV
			// データベース接続
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER, DBInfo.PASS);
			request.setAttribute("Success", true);
			// CSV出力
			if (action != null) {
				if (action.equals("商品別売上集計CSV")) {
					// 商品売上取得
					stmt = conn.prepareStatement("SELECT M.product_code, M.product_name, M.price, T.quantity, M.price * T.quantity FROM m_product AS M LEFT OUTER JOIN (SELECT product_code, SUM(quantity) AS quantity FROM t_sales GROUP BY product_code) AS T ON M.product_code = T.product_code ORDER BY M.product_code ASC;");
					// CSV生成
					generateCSV(response, stmt.executeQuery(), "sales_by_product.csv");
				} else if (action.equals("指定年月商品別売上集計CSV")) {
					// 商品売上取得
					final String month = request.getParameter("month");
					request.setAttribute("Month", month);
					stmt = conn.prepareStatement("SELECT M.product_code, M.product_name, M.price, T.quantity, M.price * T.quantity FROM m_product AS M INNER JOIN (SELECT product_code, SUM(quantity) AS quantity FROM t_sales WHERE sales_date LIKE ? GROUP BY product_code) AS T ON M.product_code = T.product_code ORDER BY M.product_code ASC;");
					stmt.setString(1, month + "-%");
					// CSV生成
					generateCSV(response, stmt.executeQuery(), "sales_in_month_" + month + ".csv");
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
		if (response.getContentType() == null) request.getRequestDispatcher("/view/output.jsp").forward(request, response);
	}
	
	/**
	 * CSV出力メソッド
	 * @param response レスポンス情報
	 * @param result SQL実行結果
	 * @param fileName 出力ファイル名(*.csv)
	 * @throws IOException
	 * @throws SQLException
	 */
	private void generateCSV(final HttpServletResponse response, final ResultSet result, final String fileName) throws IOException, SQLException {
		response.setContentType("text/csv;charset=Shift-JIS");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		PrintWriter out = response.getWriter();
		out.println("商品コード,商品名,単価,数量,金額");
		while (result.next()) {
			List<String> record = new ArrayList<>();
			for (int i = 1; i < 6; i++) {
				String field = result.getString(i);
				record.add(field != null ? field : "0");
			}
			out.println(String.join(",", record));
		}
		out.close();
	}
}