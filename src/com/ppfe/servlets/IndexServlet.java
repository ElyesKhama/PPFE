package com.ppfe.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.common.hash.Hashing;
import com.ppfe.dao.PurchaseDAO;
import com.ppfe.dao.WarningDAO;
import com.ppfe.entities.Purchase;
import com.ppfe.entities.Warning;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VUE = "/WEB-INF/index.jsp";

	private static final String ATTRIBUTE_LIST_PURCHASES = "listePurchases";
	private static final String ATTRIBUTE_LIST_WARNINGS = "listWarnings";
	private static final String ATTRIBUTE_JSON = "json";

	private static final Logger logger = LogManager.getLogger(IndexServlet.class);

	@EJB
	private PurchaseDAO purchaseDao;
	@EJB
	private WarningDAO warningDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("--- Index Servlet Rreached ---");

		ArrayList<Purchase> listPurchases = new ArrayList<Purchase>();
		listPurchases = purchaseDao.read("all");

		ArrayList<Warning> listWarnings = new ArrayList<Warning>();
		listWarnings = warningDao.readDay(0);

		// initialization of Calendar
		Calendar calendar = Calendar.getInstance();
		Date date;

		// initialization of JSon arrays
		JsonArrayBuilder arrayDayWarnings = Json.createArrayBuilder();
		JsonArrayBuilder arrayCountWarnings = Json.createArrayBuilder();

		int i;
		for (i = 0; i < 7; i++) {
			date = new Date(calendar.getTimeInMillis());
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			// TODO: check what add(index,String) is not working...
			arrayDayWarnings.add(date.toString());
			arrayCountWarnings.add(warningDao.count(i));
		}

		JsonArray valueArrayDayWarnings = arrayDayWarnings.build();
		JsonArray valueArrayCountWarnings = arrayCountWarnings.build();

		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("listDayWarnings", valueArrayDayWarnings);
		builder.add("listCountWarnings", valueArrayCountWarnings);
		JsonObject jsonObject = builder.build();

		System.out.println("JSON : \n " + jsonObject.toString());


		req.setAttribute(ATTRIBUTE_JSON, jsonObject);
		req.setAttribute(ATTRIBUTE_LIST_PURCHASES, listPurchases);
		req.setAttribute(ATTRIBUTE_LIST_WARNINGS, listWarnings);

		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

}
