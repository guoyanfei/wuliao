package com.wuliao.dandan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wuliao.dandan.model.Admin;
import com.wuliao.dandan.util.db.ConnectionUtil;

public class AdminService {

	public int getCount(String username) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from admin ");
		if (StringUtils.isNotBlank(username)) {
			sql.append("where username like '%" + username + "%'");
		}
		PreparedStatement pre = con.prepareStatement(sql.toString());

		int i = 0;
		ResultSet res = pre.executeQuery();
		while (res.next()) {
			i = res.getInt(1);
		}
		ConnectionUtil.close_res_pre_con(res, pre, con);
		return i;
	}

	public List<Admin> findAdminPage(int page, int row, String username) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		List<Admin> adminList = new ArrayList<Admin>();

		int start = (page - 1) * row;

		StringBuilder sql = new StringBuilder();
		sql.append("select * from admin ");
		if (StringUtils.isNotBlank(username)) {
			sql.append("where username like '%" + username + "%'");
		}
		sql.append(" order by id desc LIMIT ?, ?");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setInt(1, start);
		pre.setInt(2, row);

		ResultSet res = pre.executeQuery();

		while (res.next()) {
			Admin admin = new Admin();
			admin.setId(res.getLong(1));
			admin.setUsername(res.getString(2));
			admin.setPassword(res.getString(3));
			admin.setAuth(res.getLong(4));
			admin.setRealname(res.getString(5));
			admin.setSex(res.getString(6));
			admin.setAge(res.getLong(7));
			adminList.add(admin);
		}
		ConnectionUtil.close_res_pre_con(res, pre, con);
		return adminList;
	}

	public Admin find(Long id) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from admin where id = ?");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setLong(1, id);

		ResultSet res = pre.executeQuery();

		Admin admin = null;
		while (res.next()) {
			admin = new Admin();
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

	public void update(Admin admin) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		StringBuilder sql = new StringBuilder();
		sql.append("update admin set username=?, password=?, realname=?, sex=?, age=?, auth=? where id = ?");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, admin.getUsername());
		pre.setString(2, admin.getPassword());
		pre.setString(3, admin.getRealname());
		pre.setString(4, admin.getSex());
		pre.setLong(5, admin.getAge());
		pre.setLong(6, admin.getAuth());
		pre.setLong(7, admin.getId());

		pre.executeUpdate();
		ConnectionUtil.close_res_pre_con(null, pre, con);
	}

	public Admin findByUsername(String username) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from admin where username = ?");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, username);

		ResultSet res = pre.executeQuery();

		Admin admin = null;
		while (res.next()) {
			admin = new Admin();
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

	public void insert(Admin admin) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into admin(username, password, realname, sex, age, auth) values(?,?,?,?,?,?)");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, admin.getUsername());
		pre.setString(2, admin.getPassword());
		pre.setString(3, admin.getRealname());
		pre.setString(4, admin.getSex());
		pre.setLong(5, admin.getAge());
		pre.setLong(6, admin.getAuth());

		pre.executeUpdate();
		ConnectionUtil.close_res_pre_con(null, pre, con);

	}

	public void delete(Long id) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();
		PreparedStatement pre = con.prepareStatement("delete from admin where id = " + id);
		pre.execute();
		ConnectionUtil.close_res_pre_con(null, pre, con);
	}
}
