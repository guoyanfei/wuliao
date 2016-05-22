package com.wuliao.dandan.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuliao.dandan.model.Admin;
import com.wuliao.dandan.service.AdminService;

@WebServlet("/EditAdminServlet")
public class EditAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();

	public EditAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));

		Admin admin = null;
		
		try {
			admin = adminService.find(id);
			if (admin == null) {
				request.setAttribute("error", "该条数据可能已经被删除！请刷新页面！");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("admin", admin);
		RequestDispatcher disp = request.getRequestDispatcher("beFilter/adminUpdate.jsp");
		disp.forward(request, response);
	}

}
