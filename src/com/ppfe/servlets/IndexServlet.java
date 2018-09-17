package com.ppfe.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IndexServlet extends HttpServlet {
	//constants
	private static final long serialVersionUID = 1L;

	private static final String VUE = "/WEB-INF/index.jsp";
	private static final Logger logger = LogManager.getLogger(IndexServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("--- Index Servlet Rreached ---");

		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

}
