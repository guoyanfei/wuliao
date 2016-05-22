package com.wuliao.dandan.servlet.storehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuliao.dandan.service.StorehouseService;

@WebServlet("/CheckComponentNameServlet")
public class CheckComponentNameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StorehouseService storehouseService = new StorehouseService();

	public CheckComponentNameServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html,charset='UTF-8'");
		PrintWriter out = response.getWriter();
		String componentName = new String(request.getParameter("componentName").getBytes("ISO-8859-1"), "UTF-8");
		String preComponentName = new String(request.getParameter("preComponentName").getBytes("ISO-8859-1"), "UTF-8");
		try {
			if (!preComponentName.equals(componentName) && storehouseService.findByComponentName(componentName) != null) {
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
