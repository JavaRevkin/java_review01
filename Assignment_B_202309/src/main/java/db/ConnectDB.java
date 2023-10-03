package db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectDB {

    private Connection db;

    // データベース接続を取得するメソッド
    public Connection getConnection() throws NamingException, SQLException {
        if (db == null || db.isClosed()) {
        	// 初期コンテキストを作成
            Context context = new InitialContext();
            // データソースをルックアップして取得
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/jsp");
            this.db = ds.getConnection();
        }
        return db;
    }

    // データベース切断を行うメソッド
    public void disconnect() {
        try {
            if (db != null) {
            	// データベース接続を閉じる
                db.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
