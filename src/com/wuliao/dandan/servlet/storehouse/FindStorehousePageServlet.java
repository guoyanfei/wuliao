package com.wuliao.dandan.servlet.storehouse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuliao.dandan.model.Storehouse;
import com.wuliao.dandan.service.StorehouseService;

@WebServlet("/FindStorehousePageServlet")
public class FindStorehousePageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final int ROW = 12;

	private StorehouseService storehouseService = new StorehouseService();

	public FindStorehousePageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;

		String page_str = new String(request.getParameter("page").getBytes("ISO-8859-1"), "UTF-8");

		String id = "";
		if (request.getParameter("componentIdSe") != null) {
			id = new String(request.getParameter("componentIdSe").getBytes("ISO-8859-1"), "UTF-8");
		}

		String componentName = "";
		if (request.getParameter("componentNameSe") != null) {
			componentName = new String(request.getParameter("componentNameSe").getBytes("ISO-8859-1"), "UTF-8");
		}

		String notEnoughTag = "";
		if (request.getParameter("notEnoughTagSe") != null) {
			notEnoughTag = new String(request.getParameter("notEnoughTagSe").getBytes("ISO-8859-1"), "UTF-8");
		}

		if (page_str != null && !"".equals(page_str)) {
			page = Integer.parseInt(page_str);
			if (page < 1) {
				page = 1;
			}
		}

		int count = 0;
		List<Storehouse> storehouses = null;
		int lastpage = 0;
		try {
			count = storehouseService.getCount(id, componentName, notEnoughTag);

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
			storehouses = storehouseService.findStorehousePage(page, ROW, id, componentName, notEnoughTag);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("storehouses", storehouses);
		request.setAttribute("componentIdSe", id);
		request.setAttribute("componentNameSe", componentName);
		request.setAttribute("notEnoughTagSe", notEnoughTag);
		request.setAttribute("lastpage", lastpage);
		request.setAttribute("nowPage", page);

		RequestDispatcher disp = request.getRequestDispatcher("beFilter/storehouseIndex.jsp");
		disp.forward(request, response);
	}

}
