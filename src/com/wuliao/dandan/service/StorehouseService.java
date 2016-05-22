package com.wuliao.dandan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wuliao.dandan.model.Storehouse;
import com.wuliao.dandan.util.db.ConnectionUtil;

public class StorehouseService {

	// notEnoughTag表示余量不足的标记，1表示不足，否则表示足
	public int getCount(String id, String componentName, String notEnoughTag) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from storehouse where id is not null ");
		if (StringUtils.isNotBlank(id)) {
			sql.append(" and id = " + id);
		}
		if (StringUtils.isNotBlank(componentName)) {
			sql.append(" and component_name like '%" + componentName + "%' ");
		}
		if (StringUtils.isNotBlank(notEnoughTag) && !notEnoughTag.equals("2")) {
			sql.append(" and quantity " + (notEnoughTag.equals("1") ? "<=" : ">") + " redline ");
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

	public List<Storehouse> findStorehousePage(int page, int row, String id, String componentName, String notEnoughTag) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		List<Storehouse> storehouseList = new ArrayList<Storehouse>();

		int start = (page - 1) * row;

		StringBuilder sql = new StringBuilder();
		sql.append("select * from storehouse where id is not null ");
		if (StringUtils.isNotBlank(id)) {
			sql.append(" and id = " + id);
		}
		if (StringUtils.isNotBlank(componentName)) {
			sql.append(" and component_name like '%" + componentName + "%' ");
		}
		if (StringUtils.isNotBlank(notEnoughTag) && !notEnoughTag.equals("2")) {
			sql.append(" and quantity " + (notEnoughTag.equals("1") ? "<=" : ">") + " redline ");
		}
		sql.append(" order by id desc LIMIT ?, ?");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setInt(1, start);
		pre.setInt(2, row);

		ResultSet res = pre.executeQuery();

		while (res.next()) {
			Storehouse storehouse = new Storehouse();
			storehouse.setId(res.getLong(1));
			storehouse.setComponentName(res.getString(2));
			storehouse.setProducer(res.getString(3));
			storehouse.setValue(res.getString(4));
			storehouse.setAgency(res.getString(5));
			storehouse.setPrice(res.getString(6));
			storehouse.setShortName(res.getString(7));
			storehouse.setPotting(res.getString(8));
			storehouse.setRemark(res.getString(9));
			storehouse.setQuantity(res.getLong(10));
			storehouse.setRedline(res.getLong(11));
			storehouseList.add(storehouse);
		}
		ConnectionUtil.close_res_pre_con(res, pre, con);
		return storehouseList;
	}

	public Storehouse find(Long id) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from storehouse where id = ?");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setLong(1, id);

		ResultSet res = pre.executeQuery();

		Storehouse storehouse = null;
		while (res.next()) {
			storehouse = new Storehouse();
			storehouse.setId(res.getLong(1));
			storehouse.setComponentName(res.getString(2));
			storehouse.setProducer(res.getString(3));
			storehouse.setValue(res.getString(4));
			storehouse.setAgency(res.getString(5));
			storehouse.setPrice(res.getString(6));
			storehouse.setShortName(res.getString(7));
			storehouse.setPotting(res.getString(8));
			storehouse.setRemark(res.getString(9));
			storehouse.setQuantity(res.getLong(10));
			storehouse.setRedline(res.getLong(11));
		}
		ConnectionUtil.close_res_pre_con(res, pre, con);
		return storehouse;
	}

	public void update(Storehouse storehouse) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		StringBuilder sql = new StringBuilder();
		sql.append("update storehouse set component_name=?, producer=?, value=?, agency=?, price=?, short_name=?, potting=?, remark=?,");
		if (storehouse.getQuantity() != null) {
			sql.append("quantity=?, ");
		}
		sql.append(" redline=? where id = ?");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, storehouse.getComponentName());
		pre.setString(2, storehouse.getProducer());
		pre.setString(3, storehouse.getValue());
		pre.setString(4, storehouse.getAgency());
		pre.setString(5, storehouse.getPrice());
		pre.setString(6, storehouse.getShortName());
		pre.setString(7, storehouse.getPotting());
		pre.setString(8, storehouse.getRemark());
		if (storehouse.getQuantity() != null) {
			pre.setLong(9, storehouse.getQuantity());
			pre.setLong(10, storehouse.getRedline());
			pre.setLong(11, storehouse.getId());
		} else {
			pre.setLong(9, storehouse.getRedline());
			pre.setLong(10, storehouse.getId());
		}

		pre.executeUpdate();
		ConnectionUtil.close_res_pre_con(null, pre, con);
	}

	public Storehouse findByComponentName(String componentName) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from storehouse where component_name = ?");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, componentName);

		ResultSet res = pre.executeQuery();

		Storehouse storehouse = null;
		while (res.next()) {
			storehouse = new Storehouse();
			storehouse.setId(res.getLong(1));
			storehouse.setComponentName(res.getString(2));
			storehouse.setProducer(res.getString(3));
			storehouse.setValue(res.getString(4));
			storehouse.setAgency(res.getString(5));
			storehouse.setPrice(res.getString(6));
			storehouse.setShortName(res.getString(7));
			storehouse.setPotting(res.getString(8));
			storehouse.setRemark(res.getString(9));
			storehouse.setQuantity(res.getLong(10));
			storehouse.setRedline(res.getLong(11));
		}
		ConnectionUtil.close_res_pre_con(res, pre, con);
		return storehouse;
	}

	public void insert(Storehouse storehouse) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into storehouse(component_name, producer, value, agency, price, short_name, potting, remark, quantity, redline) values(?,?,?,?,?,?,?,?,?,?)");
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, storehouse.getComponentName());
		pre.setString(2, storehouse.getProducer());
		pre.setString(3, storehouse.getValue());
		pre.setString(4, storehouse.getAgency());
		pre.setString(5, storehouse.getPrice());
		pre.setString(6, storehouse.getShortName());
		pre.setString(7, storehouse.getPotting());
		pre.setString(8, storehouse.getRemark());
		pre.setLong(9, storehouse.getQuantity());
		pre.setLong(10, storehouse.getRedline());

		pre.executeUpdate();
		ConnectionUtil.close_res_pre_con(null, pre, con);

	}

	public void delete(Long id) throws ClassNotFoundException, SQLException {
		ConnectionUtil mycon = ConnectionUtil.getConnectionUtil();
		Connection con = mycon.getCon();
		PreparedStatement pre = con.prepareStatement("delete from storehouse where id = " + id);
		pre.execute();
		ConnectionUtil.close_res_pre_con(null, pre, con);
	}
}
