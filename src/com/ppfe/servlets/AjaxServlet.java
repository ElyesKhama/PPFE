package com.ppfe.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

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

import com.ppfe.dao.WarningDAO;
import com.ppfe.entities.Purchase;
import com.ppfe.entities.Warning;

public class AjaxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(AjaxServlet.class);

	@EJB
	private WarningDAO warningDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("--- Servlet Reached ---");

		// Initialization of writer
		PrintWriter out = resp.getWriter();

		// Recovery of parameter
		String paramDate = req.getParameter("dateDay");

		// Initalization nb of days
		int nbDays = initializeNbDays(paramDate);

		// Create JsonArrays for days and counts
		JsonArray[] jsonTable = new JsonArray[3];
		jsonTable = initializeJsonArrays(nbDays);

		JsonObject json = createJson(jsonTable);
		System.out.println("json  :: \n" + json.toString());
		out.print(json.toString());
	}


	public ArrayList<Warning> initializeWarnings(int nbDays) {
		ArrayList<Warning> listWarnings = new ArrayList<Warning>();
		listWarnings = warningDao.readDay(nbDays);

		return listWarnings;
	}

	public int initializeNbDays(String paramDate) {
		int nbDays = 0;
		switch (paramDate) {
		case "Today":
			nbDays = 0;
			break;
		case "Week":
			nbDays = 7;
			break;
		case "Month":
			nbDays = 30;
			break;
		default:
			logger.error("wrong parameter in ajax call");
			break;
		}
		return nbDays;
	}

	public JsonArray[] initializeJsonArrays(int nbDays) {
		JsonArray[] jsonTable = new JsonArray[3];

		// initialization of Calendar
		Calendar calendar = Calendar.getInstance();
		Date date;

		// initialization of JSon arrays
		JsonArrayBuilder arrayDayWarnings = Json.createArrayBuilder();
		JsonArrayBuilder arrayCountWarnings = Json.createArrayBuilder();
		JsonArrayBuilder arrayWarnings = Json.createArrayBuilder();

		// Initialization list of Warnings for the specified time
		ArrayList<Warning> listWarnings = new ArrayList<Warning>();
		listWarnings = initializeWarnings(nbDays);

		int i;
		for (i = 0; i < nbDays; i++) {
			date = new Date(calendar.getTimeInMillis());
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			// TODO: check what add(index,String) is not working...
			arrayDayWarnings.add(date.toString());
			arrayCountWarnings.add(warningDao.count(i));
		}

		for (i = 0; i < listWarnings.size(); i++) {
			arrayWarnings.add(createJsonObjectWarning(listWarnings.get(i)));
		}

		jsonTable[0] = arrayDayWarnings.build();
		jsonTable[1] = arrayCountWarnings.build();
		jsonTable[2] = arrayWarnings.build();

		return jsonTable;
	}
	
	public JsonObjectBuilder createJsonObjectWarning(Warning warning) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id", warning.getId());
		builder.add("voucherType", warning.getPurchase().getVoucherType().getDescription());
		builder.add("difference", warning.getCountDifference());
		builder.add("priority", warning.getPriority());
		return builder;
	}

	public JsonObject createJson(JsonArray[] jsonTable) {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("listDayWarnings", jsonTable[0]);
		builder.add("listCountWarnings", jsonTable[1]);
		builder.add("listWarnings", jsonTable[2]);

		JsonObject jsonObject = builder.build();

		return jsonObject;
	}
}
