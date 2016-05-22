package com.wuliao.dandan.servlet.storehouse;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuliao.dandan.model.Log;
import com.wuliao.dandan.service.LogService;
import com.wuliao.dandan.service.StorehouseService;

@WebServlet("/DeleteStorehouseServlet")
public class DeleteStorehouseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StorehouseService storehouseService = new StorehouseService();

	private LogService logService = new LogService();

	public DeleteStorehouseServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));

		String componentName = "";
		if (request.getParameter("componentName") != null) {
			componentName = new String(request.getParameter("componentName").getBytes("ISO-8859-1"), "UTF-8");
		}

		String username = "";
		if (request.getParameter("username") != null) {
			username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		}

		try {
			storehouseService.delete(id);

			Log log = new Log();
			log.setStorehouseId(id);
			log.setComponentName(componentName);
			log.setUsername(username);
			log.setTag(4l);
			log.setQuantity(0l);
			logService.insert(log);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("FindStorehousePageServlet?page=1").forward(request, response);
	}

}
