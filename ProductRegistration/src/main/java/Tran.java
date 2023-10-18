import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import pack.StrOpe;

public class Tran {

	private static final String URL = "jdbc:mysql://localhost/product"; // JDMC接続先情報

	private static final String USER = "root"; // ユーザー名

	private static final String PASS = "work"; // パスワード

	public static final int COLUMN_WIDTH = 20;

	public static void main(String[] args) {
		// 登録対象商品データ
		final List<ProductRecord> productData = Arrays.asList(
			new ProductRecord("001", "デスクトップPC", 150000),
			new ProductRecord("002", "ノートPC", 130000),
			new ProductRecord("010", "モニター", 80000),
			new ProductRecord("020", "キーボード", 5000),
			new ProductRecord("021", "マウス", 1000)
		);
		// 登録対象売上データ
		final List<SalesRecord> salesData = Arrays.asList(
			new SalesRecord(Date.valueOf("2023-10-01"), "001", 4),
			new SalesRecord(Date.valueOf("2023-10-01"), "002", 6),
			new SalesRecord(Date.valueOf("2023-10-01"), "020", 13),
			new SalesRecord(Date.valueOf("2023-10-02"), "001", 15),
			new SalesRecord(Date.valueOf("2023-10-02"), "020", 8),
			new SalesRecord(Date.valueOf("2023-10-02"), "021", 12),
			new SalesRecord(Date.valueOf("2023-10-04"), "001", 1),
			new SalesRecord(Date.valueOf("2023-10-04"), "002", 3),
			new SalesRecord(Date.valueOf("2023-10-04"), "020", 20),
			new SalesRecord(Date.valueOf("2023-10-04"), "021", 35)
		);
		// データベース操作
		try(final Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			conn.setAutoCommit(false);
			try {
				// 変数宣言
				PreparedStatement stmt = null;
				ResultSet rs = null;
				int colNum = 0;
				// 売上テーブルのデータを全件削除
				stmt = conn.prepareStatement("DELETE FROM t_sales;");
				stmt.executeUpdate();
				System.out.println("\n売上テーブルのデータを削除しました");
				// 商品マスタのデータを全件削除
				stmt = conn.prepareStatement("DELETE FROM m_product;");
				stmt.executeUpdate();
				System.out.println("\n商品マスタのデータを削除しました");
				// 商品マスタにデータを登録
				stmt = conn.prepareStatement("INSERT INTO m_product VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL);");
				for(ProductRecord product : productData) {
					stmt.setString(1, product.code);
					stmt.setString(2, product.name);
					stmt.setInt(3, product.price);
					stmt.executeUpdate();
				}
				System.out.println("\n商品マスタにデータを登録しました");
				// 売上テーブルみデータを登録
				stmt = conn.prepareStatement("INSERT INTO t_sales VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);");
				for(SalesRecord sales : salesData) {
					stmt.setDate(1, sales.date);
					stmt.setString(2, sales.code);
					stmt.setInt(3, sales.quantity);
					stmt.executeUpdate();
				}
				System.out.println("\n売上テーブルにデータを登録しました");
				// 商品マスタのデータを全件表示
				stmt = conn.prepareStatement("SELECT * FROM m_product;");
				rs = stmt.executeQuery();
				colNum = rs.getMetaData().getColumnCount();
				System.out.println("\n商品マスタのデータを表示します");
				Arrays.asList("商品コード", "商品名", "単価", "登録日時", "更新日時", "削除日時").forEach(str -> System.out.print(StrOpe.leftAlignment(str, COLUMN_WIDTH)));
				System.out.println();
				while (rs.next()) {
					for (int i = 0; i < colNum; i++) System.out.print(StrOpe.leftAlignment(StrOpe.numForm(rs.getString(i + 1)), COLUMN_WIDTH));
					System.out.println();
				}
				// 売上テーブルのデータを全件表示
				stmt = conn.prepareStatement("SELECT * FROM t_sales;");
				rs = stmt.executeQuery();
				colNum = rs.getMetaData().getColumnCount();
				System.out.println("\n売上テーブルのデータを表示します");
				Arrays.asList("売上日", "商品コード", "数量", "登録日時", "更新日時").forEach(str -> System.out.print(StrOpe.leftAlignment(str, COLUMN_WIDTH)));
				System.out.println();
				while (rs.next()) {
					for (int i = 0; i < colNum; i++) System.out.print(StrOpe.leftAlignment(rs.getString(i + 1), COLUMN_WIDTH));
					System.out.println();
				}
				// コミット
				conn.commit();
			} catch (Exception e) {
				// ロールバック
				conn.rollback();
				System.out.println("エラーが発生した為ロールバックしました");
			}
		} catch (SQLException e) {
			System.out.println("データベースにアクセスできませんでした");
		}
	}

	private record ProductRecord(String code, String name, int price) {
	}

	private record SalesRecord(Date date, String code, int quantity) {
	}
}