package com.wuliao.dandan.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuliao.dandan.model.Admin;
import com.wuliao.dandan.service.AdminService;

@WebServlet("/FindAdminPageServlet")
public class FindAdminPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();

	private static final int ROW = 3;

	public FindAdminPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int page = 1;

		String page_str = new String(request.getParameter("page").getBytes("ISO-8859-1"), "UTF-8");

		String name = "";
		if (request.getParameter("name") != null) {
			name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		}

		if (page_str != null && !"".equals(page_str)) {
			page = Integer.parseInt(page_str);
			if (page < 1) {
				page = 1;
			}
		}

		int count = 0;
		List<Admin> admins = null;
		int lastpage = 0;
		try {
			count = adminService.getCount(name);

			if (count == 0) {
				lastpage = 1;
			} else if (count % ROW == 0) {
				lastpage = count / ROW;
			} else {
				lastpage = count / ROW + 1;
			}
			if (page >= lastpage) {
				page = lastpage;
			}
			admins = adminService.findAdminPage(page, ROW, name);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("admins", admins);
		request.setAttribute("name", name);
		request.setAttribute("lastpage", lastpage);
		request.setAttribute("nowPage", page);

		RequestDispatcher disp = request.getRequestDispatcher("beFilter/adminIndex.jsp");
		disp.forward(request, response);
	}

}
