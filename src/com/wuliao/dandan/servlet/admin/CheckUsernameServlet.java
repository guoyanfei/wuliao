package com.wuliao.dandan.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuliao.dandan.service.AdminService;

@WebServlet("/CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();

	public CheckUsernameServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html,charset='UTF-8'");
		PrintWriter out = response.getWriter();
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		String preUsername = new String(request.getParameter("preUsername").getBytes("ISO-8859-1"), "UTF-8");
		try {
			if (!preUsername.equals(username) && adminService.findByUsername(username) != null) {
				out.print("false");
			} else {
				out.print("true");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
}
