package com.wuliao.dandan.servlet.storehouse;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuliao.dandan.model.Admin;
import com.wuliao.dandan.model.Log;
import com.wuliao.dandan.model.Storehouse;
import com.wuliao.dandan.service.LogService;
import com.wuliao.dandan.service.StorehouseService;

@WebServlet("/UpdateStorehouseServlet")
public class UpdateStorehouseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StorehouseService storehouseService = new StorehouseService();

	private LogService logService = new LogService();

	public UpdateStorehouseServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = 0l;
		if (request.getParameter("id") != null) {
			id = Long.valueOf(request.getParameter("id"));
		}
		String componentName = "";
		if (request.getParameter("componentName") != null) {
			componentName = new String(request.getParameter("componentName").getBytes("ISO-8859-1"), "UTF-8");
		}
		String producer = "";
		if (request.getParameter("producer") != null) {
			producer = new String(request.getParameter("producer").getBytes("ISO-8859-1"), "UTF-8");
		}
		String value = "";
		if (request.getParameter("value") != null) {
			value = new String(request.getParameter("value").getBytes("ISO-8859-1"), "UTF-8");
		}
		String agency = "";
		if (request.getParameter("agency") != null) {
			agency = new String(request.getParameter("agency").getBytes("ISO-8859-1"), "UTF-8");
		}
		String price = "";
		if (request.getParameter("price") != null) {
			price = new String(request.getParameter("price").getBytes("ISO-8859-1"), "UTF-8");
		}
		String shortName = "";
		if (request.getParameter("shortName") != null) {
			shortName = new String(request.getParameter("shortName").getBytes("ISO-8859-1"), "UTF-8");
		}
		String potting = "";
		if (request.getParameter("potting") != null) {
			potting = new String(request.getParameter("potting").getBytes("ISO-8859-1"), "UTF-8");
		}
		String remark = "";
		if (request.getParameter("remark") != null) {
			remark = new String(request.getParameter("remark").getBytes("ISO-8859-1"), "UTF-8");
		}
		Long quantity = null;
		if (request.getParameter("quantity") != null) {
			quantity = Long.valueOf(request.getParameter("quantity"));
		}
		Long redline = null;
		if (request.getParameter("redline") != null) {
			redline = Long.valueOf(request.getParameter("redline"));
		}
		Long outQuantity = null;
		if (request.getParameter("outQuantity") != null) {
			outQuantity = Long.valueOf(request.getParameter("outQuantity"));
		}
		Long inQuantity = null;
		if (request.getParameter("inQuantity") != null) {
			inQuantity = Long.valueOf(request.getParameter("inQuantity"));
		}

		Storehouse storehouse = new Storehouse();
		storehouse.setId(id);
		storehouse.setComponentName(componentName);
		storehouse.setProducer(producer);
		storehouse.setValue(value);
		storehouse.setAgency(agency);
		storehouse.setPrice(price);
		storehouse.setShortName(shortName);
		storehouse.setPotting(potting);
		storehouse.setRemark(remark);
		storehouse.setRedline(redline);
		if (outQuantity != null) {
			storehouse.setQuantity(quantity - outQuantity);
		} else if (inQuantity != null) {
			storehouse.setQuantity(quantity + inQuantity);
		} else {
			storehouse.setQuantity(quantity);
		}
		try {
			storehouseService.update(storehouse);

			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("admin");

			Log log = new Log();
			log.setStorehouseId(id);
			log.setComponentName(componentName);
			log.setUsername(admin.getUsername());
			if (outQuantity != null) {
				log.setTag(1l);
				log.setQuantity(outQuantity);
			} else if (inQuantity != null) {
				log.setTag(2l);
				log.setQuantity(inQuantity);
			} else {
				log.setTag(3l);
				log.setQuantity(0l);
			}
			logService.insert(log);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("FindStorehousePageServlet?page=1").forward(request, response);
	}

}
