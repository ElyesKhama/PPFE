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
	private static final Logger logger = LogManager.getLogger(IndexServlet.class);

	private static final String ATT_SESSION_USER = "user";
	private static final String VUE = "/WEB-INF/index.jsp";
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDAO userDao;
	
	private static final String ATT_USER = "user";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("inputUsername");		
		
		LoginForm form = new LoginForm();
		User user = form.loginUser(req,userDao);
		boolean success = form.isSuccess();
		if(success == true) {
			logger.info("AUTENTIFICATION SUCCESSFUL");
			HttpSession session = req.getSession();
			session.setAttribute(ATT_SESSION_USER, user);
		}
		else {
			//ERROR DURING CONNECTION
			logger.info("ERROR DURING AUTHENTIFICATION");
		}
		
		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}
	
}	
