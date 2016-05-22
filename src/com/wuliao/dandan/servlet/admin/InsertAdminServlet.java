package com.wuliao.dandan.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuliao.dandan.model.Admin;
import com.wuliao.dandan.service.AdminService;

@WebServlet("/InsertAdminServlet")
public class InsertAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();

	public InsertAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = "";
		if (request.getParameter("username") != null) {
			username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		}
		String password = "";
		if (request.getParameter("password") != null) {
			password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
		}
		String realname = "";
		if (request.getParameter("realname") != null) {
			realname = new String(request.getParameter("realname").getBytes("ISO-8859-1"), "UTF-8");
		}
		String sex = "";
		if (request.getParameter("sex") != null) {
			sex = new String(request.getParameter("sex").getBytes("ISO-8859-1"), "UTF-8");
		}
		Long age = 0l;
		if (request.getParameter("age") != null) {
			age = Long.valueOf(request.getParameter("age"));
		}
		Long auth = 0l;
		if (request.getParameter("auth") != null) {
			auth = Long.valueOf(request.getParameter("auth"));
		}

		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setRealname(realname);
		admin.setSex(sex);
		admin.setAge(age);
		admin.setAuth(auth);

		try {
			adminService.insert(admin);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("FindAdminPageServlet?page=1").forward(request, response);
	}

}
