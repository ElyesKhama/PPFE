package com.ppfe.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ppfe.dao.PurchaseDAO;
import com.ppfe.entities.Purchase;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VUE = "/WEB-INF/index.jsp";
	private static final String ATTRIBUTE_LIST_PURCHASES = "listePurchases";
	private static final Logger logger = LoggerFactory.getLogger(IndexServlet.class);
	
	@EJB
	private PurchaseDAO purchaseDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("--- Servlet Index reached ---");
		
		HttpSession session = req.getSession();
		
		
		ArrayList<Purchase> listPurchases = new ArrayList<Purchase>();
		listPurchases = purchaseDao.read("all");
		session.setAttribute(ATTRIBUTE_LIST_PURCHASES, listPurchases);
	
		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

}
