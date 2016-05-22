package com.wuliao.dandan.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuliao.dandan.model.Admin;
import com.wuliao.dandan.service.LoginService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private LoginService loginService = new LoginService();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
		Long auth = Long.valueOf(request.getParameter("auth"));

		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setAuth(auth);

		try {
			admin = loginService.checkLoginInfo(admin);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (admin.getAuth() != null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			if (admin.getAuth() == 0) {
				request.getRequestDispatcher("FindAdminPageServlet?page=1").forward(request, response);
				return;
			} else if (admin.getAuth() == 1) {
				request.getRequestDispatcher("FindStorehousePageServlet?page=1").forward(request, response);
				return;
			} else if (admin.getAuth() == 2) {
				request.getRequestDispatcher("FindStorehousePageServlet?page=1").forward(request, response);
				return;
			}else {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
		}
		request.setAttribute("error", "<font color=\"red\">不好意思，帐号或密码错误！</font>");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
