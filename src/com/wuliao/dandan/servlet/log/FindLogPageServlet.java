package com.wuliao.dandan.servlet.log;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuliao.dandan.model.Log;
import com.wuliao.dandan.service.LogService;

@WebServlet("/FindLogPageServlet")
public class FindLogPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final int ROW = 12;

	private LogService logService = new LogService();

	public FindLogPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;

		String page_str = new String(request.getParameter("page").getBytes("ISO-8859-1"), "UTF-8");

		String username = "";
		if (request.getParameter("usernameSe") != null) {
			username = new String(request.getParameter("usernameSe").getBytes("ISO-8859-1"), "UTF-8");
		}

		String componentName = "";
		if (request.getParameter("componentNameSe") != null) {
			componentName = new String(request.getParameter("componentNameSe").getBytes("ISO-8859-1"), "UTF-8");
		}

		String tag = "";
		if (request.getParameter("tagSe") != null) {
			tag = new String(request.getParameter("tagSe").getBytes("ISO-8859-1"), "UTF-8");
		}

		String queryBegintime = "";
		if (request.getParameter("queryBegintime") != null) {
			queryBegintime = new String(request.getParameter("queryBegintime").getBytes("ISO-8859-1"), "UTF-8");
		}

		String queryEndtime = "";
		if (request.getParameter("queryEndtime") != null) {
			queryEndtime = new String(request.getParameter("queryEndtime").getBytes("ISO-8859-1"), "UTF-8");
		}

		Log searchLog = new Log();
		searchLog.setUsername(username);
		searchLog.setComponentName(componentName);
		searchLog.setTag(tag.equals("") ? null : Long.valueOf(tag));

		if (page_str != null && !"".equals(page_str)) {
			page = Integer.parseInt(page_str);
			if (page < 1) {
				page = 1;
			}
		}

		int count = 0;
		List<Log> logs = null;
		int lastpage = 0;
		try {
			count = logService.getCount(searchLog, queryBegintime, queryEndtime);

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
			logs = logService.findLogPage(page, ROW, searchLog, queryBegintime, queryEndtime);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("logs", logs);
		request.setAttribute("usernameSe", username);
		request.setAttribute("componentNameSe", componentName);
		request.setAttribute("tagSe", tag);
		request.setAttribute("queryBegintime", queryBegintime);
		request.setAttribute("queryEndtime", queryEndtime);
		request.setAttribute("lastpage", lastpage);
		request.setAttribute("nowPage", page);

		RequestDispatcher disp = request.getRequestDispatcher("/beFilter/logIndex.jsp");
		disp.forward(request, response);
	}

}
