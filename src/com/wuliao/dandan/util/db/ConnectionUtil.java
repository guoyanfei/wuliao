package com.wuliao.dandan.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

	private Connection con = null;
	private String username = "root";
	private String password = "";
	private String DBName = "wuliao";
	private String url = "jdbc:mysql://localhost:3306/" + DBName + "?useUnicode=true&characterEncoding=UTF-8";
	private static ConnectionUtil mycon = null;

	private ConnectionUtil() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	public static ConnectionUtil getConnectionUtil() throws ClassNotFoundException {
		if (mycon == null) {
			mycon = new ConnectionUtil();
		}
		return mycon;
	}

	public Connection getCon() throws SQLException {
		this.con = DriverManager.getConnection(this.url, this.username, this.password);
		return this.con;
	}

	public static void close_res_pre_con(ResultSet res, PreparedStatement pre, Connection con) throws SQLException {
		if (res != null) {
			res.close();
		}
		if (pre != null) {
			pre.close();
		}
		if (con != null) {
			con.close();
		}
	}

}
