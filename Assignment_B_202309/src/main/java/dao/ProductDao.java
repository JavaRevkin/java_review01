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

/**
 * このクラスは、Product データベーステーブルと対話するための Data Access Object (DAO) を表します。
 */

public class ProductDao {

	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;
	private ConnectDB connectdb;
	
	
	/**
     * ProductDao クラスのコンストラクター。ConnectDB インスタンスを初期化します。
     */
	
	public ProductDao() {
		connectdb = new ConnectDB();
    }
	
	
	/**
     * データベース接続を確立します。
     * @throws NamingException データベースへの接続時にネーミング例外が発生した場合。
     * @throws SQLException データベースへの接続時に SQL 例外が発生した場合。
     */
	
    private void getConnection() throws NamingException, SQLException {
        db = connectdb.getConnection();
    }

  /* private void disconnect() {
        connectdb.disconnect();
    } */

    
    /**
     * データベースからすべての製品を取得します。
     * @return すべての製品を表す Product オブジェクトの ArrayList。
     */
    
	public ArrayList<Product> getAllProduct() {
		ArrayList<Product> products = new ArrayList<>();
		try {
			this.getConnection();
			ps = db.prepareStatement(
					"SELECT * FROM m_product WHERE `delete_datetime` IS NULL ORDER BY `product_code` ASC;");
			rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setProductCode(rs.getInt("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setRegisterDatetime(rs.getString("register_datetime"));
				product.setUpdateDatetime(rs.getString("update_datetime"));
				product.setDeleteDatetime(rs.getString("delete_datetime"));
				products.add(product);
			}
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}

		return products;
	}

	
	/**
     * 検索パラメータに基づいて製品を検索します。
     * @param searchParams 製品名で製品をフィルタリングするための検索パラメータ。
     * @return 検索結果を表す Product オブジェクトの ArrayList。
     */
	
	public ArrayList<Product> searchProduct(String searchParams) {
		ArrayList<Product> products = new ArrayList<>();
		try {
			this.getConnection();

			ps = db.prepareStatement("SELECT * FROM m_product WHERE product_name like ?");
			ps.setString(1, "%" + searchParams + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setProductCode(rs.getInt("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setRegisterDatetime(rs.getString("register_datetime"));
				product.setUpdateDatetime(rs.getString("update_datetime"));
				product.setDeleteDatetime(rs.getString("delete_datetime"));
				products.add(product);
			}
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}

		return products;
	}
	
	
	/**
     * データベースに新しい製品を挿入します。
     * @param product 挿入する製品を表す Product オブジェクト。
     */

	public void insertOne(Product product) {
		try {
			this.getConnection();
			ps = db.prepareStatement("INSERT INTO m_product (product_name, price) VALUES(?,?);");
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getPrice());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}
	}
	
	
    /**
     * 一意の製品コードで製品を取得します。
     * @param id 取得する製品の製品コード。
     * @return 取得した製品を表す Product オブジェクト。
     */
	
	public Product findOne(int id) {
		Product product = new Product();
		try {
			this.getConnection();
			ps = db.prepareStatement("SELECT * FROM m_product WHERE product_code=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				product.setProductCode(rs.getInt("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setRegisterDatetime(rs.getString("register_datetime"));
				product.setUpdateDatetime(rs.getString("update_datetime"));
				product.setDeleteDatetime(rs.getString("delete_datetime"));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}

		return product;
	}

	/**
     * データベース内の既存の製品を更新します。
     * @param product 更新された製品を表す Product オブジェクト。
     */
	
	public void updateOne(Product product) {
		try {
			this.getConnection();
			ps = db.prepareStatement("UPDATE m_product SET product_name=?,price=? WHERE product_code=?");
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getPrice());
			ps.setInt(3, product.getProductCode());
			ps.executeUpdate();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectdb.disconnect();
		}
	}

	/**
     * 製品を削除し、delete_datetime を設定してデータベースから削除します。
     * @param id 削除する製品の製品コード。
     * @return 製品が正常に削除された場合は true、それ以外の場合は false。
     */
	
	public boolean deleteOne(int id) {
		try {
			this.getConnection();
			ps = db.prepareStatement("UPDATE m_product SET delete_datetime=CURRENT_TIMESTAMP WHERE product_code=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			connectdb.disconnect();
		}
	}

}