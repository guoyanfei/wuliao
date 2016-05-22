package com.wuliao.dandan.servlet.storehouse;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuliao.dandan.model.Storehouse;
import com.wuliao.dandan.service.StorehouseService;

@WebServlet("/EditStorehouseServlet")
public class EditStorehouseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StorehouseService storehouseService = new StorehouseService();

	public EditStorehouseServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		Long tag = Long.valueOf(request.getParameter("tag"));
		Storehouse storehouse = null;

		try {
			storehouse = storehouseService.find(id);
			if (storehouse == null) {
				request.setAttribute("error", "该条数据可能已经被删除！请刷新页面！");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("storehouse", storehouse);
		RequestDispatcher disp = null;
		if (tag == 1l) {
			disp = request.getRequestDispatcher("beFilter/storehouseUpdate.jsp");
		} else if (tag == 2l) {
			disp = request.getRequestDispatcher("beFilter/storehouseIn.jsp");
		} else {
			disp = request.getRequestDispatcher("beFilter/storehouseOut.jsp");
		}
		disp.forward(request, response);
	}
}
