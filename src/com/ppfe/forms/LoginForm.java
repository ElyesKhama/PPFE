package com.ppfe.forms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ppfe.dao.UserDAO;
import com.ppfe.entities.User;
import com.ppfe.servlets.AjaxServlet;

import sun.nio.cs.StandardCharsets;

public class LoginForm {

	private static final Logger logger = LogManager.getLogger(AjaxServlet.class);

	private static final String INPUT_USERNAME = "inputUsername";
	private static final String INPUT_PASSWORD = "inputPassword";

	private boolean success = false;

	public User loginUser(HttpServletRequest req, UserDAO userDao) {
		User user = null;

		// recover parameters sent in the form
		String username = req.getParameter(INPUT_USERNAME);
		String password = req.getParameter(INPUT_PASSWORD);

		// method to hash the password
		String passwordHash = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256"); // encrypting used : SHA-256
			byte[] hash = digest.digest(password.getBytes());
			passwordHash = DatatypeConverter.printHexBinary(hash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try to read the user in the DB
		user = userDao.read(username);

		if (user != null) {
			// found a user, : compare password
			if (passwordHash.equalsIgnoreCase(user.getPassword())) {
				success = true; // connection ok
			}
		}
		return user;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
