package com.ppfe.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	// constants
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(IndexServlet.class);
	public static final String URL_REDIRECTION = "/PPFE/login";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("--- Logout Servlet Reached ---");
		HttpSession session = req.getSession(); // recov the session
		session.invalidate(); // destroy the session

		// redirect to the login page
		resp.sendRedirect(URL_REDIRECTION);
	}

}
