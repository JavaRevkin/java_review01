package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import db.ConnectDB;
import model.Product;
import model.Sales;

public class SalesDao {

	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;
	private ConnectDB connectdb;

	public SalesDao() {
		connectdb = new ConnectDB();
    }

    private void getConnection() throws NamingException, SQLException {
        db = connectdb.getConnection();
    }
	
	// 今日の売上データをすべて取得するメソッド
	public ArrayList<Sales> getAllTodaysSales() {
		ArrayList<Sales> sales = new ArrayList<>();
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		try {
			this.getConnection();
			ps = db.prepareStatement(
					"SELECT `t_sales`.`sales_date`,`t_sales`.`product_code`, `t_sales`.`quantity`,`t_sales`.`register_datetime`,`t_sales`.`update_datetime`,`m_product`.`product_name`  FROM `t_sales` "
							+ "INNER JOIN `m_product` ON t_sales.product_code = m_product.product_code WHERE CAST( `sales_date` AS DATE) = CAST( ? AS DATE) ;");
			ps.setString(1, timestamp.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				Sales sale = new Sales();
				sale.setProductCode(rs.getInt("product_code"));
				sale.setQuantity(rs.getInt("quantity"));
				sale.setSalesDate(rs.getString("sales_date"));
				sale.setRegisterDatetime(rs.getString("register_datetime"));
				sale.setUpdateDatetime(rs.getString("update_datetime"));
				sale.setProductName(rs.getString("product_name"));
				sales.add(sale);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}

		return sales;
	}

	// 売上データを1件挿入するメソッド
	public void insertOne(Sales sales) {
		try {
			this.getConnection();
			ps = db.prepareStatement("INSERT INTO t_sales (sales_date, product_code, quantity) VALUES(?,?,?) ;");
			ps.setString(1, sales.getSalesDate());
			ps.setInt(2, sales.getProductCode());
			ps.setInt(3, sales.getQuantity());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}
	}

	// 売上データを1件更新するメソッド
	public void UpdateOne(Sales sales) {
		try {
			this.getConnection();
			ps = db.prepareStatement(
					"UPDATE t_sales SET quantity=? WHERE product_code=? AND CAST( `sales_date` AS DATE) = CAST( ? AS DATE);");
			ps.setInt(1, sales.getQuantity());
			ps.setInt(2, sales.getProductCode());
			ps.setString(3, sales.getSalesDate());
			System.out.println(sales.getSalesDate());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}
	}

	// 特定の製品コードに対する売上データの存在を検索するメソッド
	public Boolean searchForSale(int id) {
		try {
			this.getConnection();

			ps = db.prepareStatement("SELECT count(*) FROM t_sales WHERE product_code = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
			return false;
		} finally {
			connectdb.disconnect();
		}
	}
	
	/**
	 * // CSVファイル出力用に全売上データを取得するメソッド
	 * @return 売上データ
	 */
	public ArrayList<Sales> getAllSalesForCSV() {
		ArrayList<Sales> sales = new ArrayList<>();
		try {
			this.getConnection();
			ps = db.prepareStatement(
					"SELECT `m_product`.`product_code`,`m_product`.`product_name`, `m_product`.`price`, SUM(`t_sales`.`quantity`) as quantity  FROM `m_product`"
							+ "LEFT OUTER JOIN `t_sales` ON t_sales.product_code = m_product.product_code  GROUP BY `m_product`.`product_code` ORDER BY `m_product`.`product_code` ASC;");
			rs = ps.executeQuery();

			while (rs.next()) {
				Sales sale = new Sales();
				sale.setProductCode(rs.getInt("product_code"));
				sale.setQuantity(rs.getInt("quantity"));
				sale.setProductName(rs.getString("product_name"));
				sale.setPrice(rs.getInt("price"));
				sales.add(sale);
			}
		} catch (NamingException | SQLException e) {
			
			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}

		return sales;
	}
    
	/**
	 * 特定の月の全売上データを取得するメソッド
	 * @param ユーザからもらった日付
	 * @return 売上データ
	 */
	public ArrayList<Sales> getSalesByDateForCSV(String date) {
		ArrayList<Sales> sales = new ArrayList<>();
		try {
			this.getConnection();
			ps = db.prepareStatement(
					"SELECT `m_product`.`product_code`,`m_product`.`product_name`, `m_product`.`price`, SUM(`t_sales`.`quantity`) as quantity  FROM `m_product`"
							+ "INNER JOIN `t_sales` ON t_sales.product_code = m_product.product_code WHERE DATE_FORMAT(`t_sales`.`sales_date`,'%Y-%m') = ? GROUP BY `m_product`.`product_code` ORDER BY `m_product`.`product_code` ASC;");
			ps.setString(1, date);
			rs = ps.executeQuery();

			while (rs.next()) {
				Sales sale = new Sales();
				sale.setProductCode(rs.getInt("product_code"));
				sale.setQuantity(rs.getInt("quantity"));
				sale.setProductName(rs.getString("product_name"));
				sale.setPrice(rs.getInt("price"));
				sales.add(sale);
			}
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}

		return sales;
	}
}