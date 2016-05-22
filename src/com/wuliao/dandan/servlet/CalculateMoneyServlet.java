package com.wuliao.dandan.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;

import com.wuliao.dandan.util.excel.ExcelReader;

@WebServlet("/CalculateMoneyServlet")
public class CalculateMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalculateMoneyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = "";
		if (request.getParameter("filePath") != null) {
			filePath = new String(request.getParameter("filePath").getBytes("ISO-8859-1"), "UTF-8");
		}

		response.setContentType("text/html,charset='UTF-8'");
		PrintWriter out = response.getWriter();

		Double money = 0.0;
		try {
			List<String[]> list = ExcelReader.readExcel(new File(filePath), 1);
			for (int i = 1; i < list.size(); i++) {
				String[] str = (String[]) list.get(i);
				money += Double.valueOf(str[2]) * Double.valueOf(str[3]);
			}
			out.print(money.toString());
		} catch (BiffException e) {
			out.print("false1");
		} catch (IOException e) {
			out.print("false2");
		} catch (NumberFormatException e) {
			out.print("false1");
		}
		out.flush();
		out.close();
	}

}
