package com.wuliao.dandan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wuliao.dandan.model.Log;
import com.wuliao.dandan.util.db.ConnectionUtil;

public class LogService {

	public int getCount(Log log, String queryBegintime, String queryEndtime) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from log where id is not null ");
		if (log != null && StringUtils.isNotBlank(log.getComponentName())) {
			sql.append(" and component_name like '%" + log.getComponentName() + "%'");
		}
		if (log != null && StringUtils.isNotBlank(log.getUsername())) {
			sql.append(" and username like '%" + log.getUsername() + "%'");
		}
		if (log != null && log.getTag() != null && log.getTag() != 0) {
			sql.append(" and tag = " + log.getTag());
		}
		if (StringUtils.isNotBlank(queryBegintime)) {
			sql.append(" and created_at > '" + queryBegintime + "'");
		}
		if (StringUtils.isNotBlank(queryEndtime)) {
			sql.append(" and created_at < '" + queryEndtime + "'");
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

	public List<Log> findLogPage(int page, int row, Log log, String queryBegintime, String queryEndtime) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		List<Log> logList = new ArrayList<Log>();

		int start = (page - 1) * row;

		StringBuilder sql = new StringBuilder();
		sql.append("select * from log where id is not null ");
		if (log != null && StringUtils.isNotBlank(log.getComponentName())) {
			sql.append(" and component_name like '%" + log.getComponentName() + "%'");
		}
		if (log != null && StringUtils.isNotBlank(log.getUsername())) {
			sql.append(" and username like '%" + log.getUsername() + "%'");
		}
		if (log != null && log.getTag() != null && log.getTag() != 0) {
			sql.append(" and tag = " + log.getTag());
		}
		if (StringUtils.isNotBlank(queryBegintime)) {
			sql.append(" and created_at > '" + queryBegintime + "'");
		}
		if (StringUtils.isNotBlank(queryEndtime)) {
			sql.append(" and created_at < '" + queryEndtime + "'");
		}
		sql.append(" order by id desc LIMIT ?, ?");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setInt(1, start);
		pre.setInt(2, row);

		ResultSet res = pre.executeQuery();

		while (res.next()) {
			Log log1 = new Log();
			log1.setId(res.getLong(1));
			log1.setStorehouseId(res.getLong(2));
			log1.setComponentName(res.getString(3));
			log1.setUsername(res.getString(4));
			log1.setTag(res.getLong(5));
			log1.setQuantity(res.getLong(6));
			log1.setCreatedAt(res.getDate(7));
			logList.add(log1);
		}
		ConnectionUtil.close_res_pre_con(res, pre, con);
		return logList;
	}

	public void insert(Log log) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into log(storehouse_id, component_name, username, tag, quantity, created_at) values(?,?,?,?,?,now())");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setLong(1, log.getStorehouseId());
		pre.setString(2, log.getComponentName());
		pre.setString(3, log.getUsername());
		pre.setLong(4, log.getTag());
		pre.setLong(5, log.getQuantity());

		pre.executeUpdate();
		ConnectionUtil.close_res_pre_con(null, pre, con);

	}

	public void delete(Long id) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();
		PreparedStatement pre = con.prepareStatement("delete from log where id = " + id);
		pre.execute();
		ConnectionUtil.close_res_pre_con(null, pre, con);
	}
}
