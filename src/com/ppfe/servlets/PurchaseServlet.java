package com.ppfe.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ppfe.dao.PurchaseDAO;
import com.ppfe.dao.WarningDAO;
import com.ppfe.entities.Purchase;
import com.ppfe.entities.Warning;

public class PurchaseServlet extends HttpServlet {

	// constants
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(IndexServlet.class);

	private static final String VUE = "/WEB-INF/purchase.jsp";
	private static final String ATT_WARNING = "warning";
	private static final String ATT_PURCHASE_PREVIOUS_DAY = "previousPurchase";

	@EJB
	private WarningDAO warningDao;
	@EJB
	private PurchaseDAO purchaseDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("--- Purchase Servlet Rreached ---");

		String paramId = req.getParameter("id");
		Warning warning = warningDao.read(Long.valueOf(paramId));

		Date date = warning.getDateDay();

		// initialization of Calendar
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1); // previous day

		date = new Date(calendar.getTimeInMillis());
		Purchase purchase = purchaseDao.read(date, warning.getPurchase().getVoucherType().getId());

		req.setAttribute(ATT_PURCHASE_PREVIOUS_DAY, purchase);
		req.setAttribute(ATT_WARNING, warning);

		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}
}
