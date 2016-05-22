package com.wuliao.dandan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wuliao.dandan.model.Admin;
import com.wuliao.dandan.util.db.ConnectionUtil;

public class LoginService {

	public Admin checkLoginInfo(Admin admin) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();
		PreparedStatement pre = con.prepareStatement("select * from admin where username = ? and password = ? and auth = ? limit 1");
		pre.setString(1, admin.getUsername());
		pre.setString(2, admin.getPassword());
		pre.setLong(3, admin.getAuth());

		ResultSet res = pre.executeQuery();
		admin = new Admin();
		while (res.next()) {
			admin.setId(res.getLong(1));
			admin.setUsername(res.getString(2));
			admin.setPassword(res.getString(3));
			admin.setAuth(res.getLong(4));
			admin.setRealname(res.getString(5));
			admin.setSex(res.getString(6));
			admin.setAge(res.getLong(7));
		}
		ConnectionUtil.close_res_pre_con(res, pre, con);
		return admin;
	}
}
