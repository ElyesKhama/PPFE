package com.ppfe.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ppfe.dao.UserDAO;
import com.ppfe.entities.User;
import com.ppfe.forms.LoginForm;

public class LoginFormServlet extends HttpServlet {
	// constants
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(IndexServlet.class);

	private static final String ATT_SESSION_USER = "user";
	private static final String ATT_REQ_RESULT = "result";
	private static final String VUE_INDEX = "/WEB-INF/login.jsp";

	@EJB
	private UserDAO userDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("--- Login Servlet Reached [GET] ---");
		this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("--- Login Servlet Reached [POST] ---");

		LoginForm form = new LoginForm();
		User user = form.loginUser(req, userDao); // try to log in
		boolean success = form.isSuccess();
		if (success == true) { // OK
			logger.info("AUTENTIFICATION SUCCESSFUL");
			HttpSession session = req.getSession();
			session.setAttribute(ATT_SESSION_USER, user);
			resp.sendRedirect(req.getContextPath() + "/index");
		} else { // NOK
			// ERROR DURING CONNECTION
			logger.info("ERROR DURING AUTHENTIFICATION");

			req.setAttribute(ATT_REQ_RESULT, success);

			// print les erreurs
			this.getServletContext().getRequestDispatcher(VUE_INDEX).forward(req, resp);
		}
	}

}
