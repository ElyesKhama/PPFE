package com.ppfe.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/* Filter to restrict access to user who are not connected 
 * all pages except login page
 * To disable/enable it add/remove commentary on the next line
 */

//@WebFilter("/*")
public class RestrictionFilter implements Filter {

	//constants
	private static final String ATT_SESSION_USER = "user";
	private static final String LOGIN_PAGE = "/login";
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		/* Cast of objects request and response */
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		/* Non-filtering of static resources */
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.startsWith("/inc")) {
			arg2.doFilter(request, response);
			return;
		}
		
		if(path.startsWith("/login")) {
			arg2.doFilter(request, response);
			return;
		}

		/* Recovering session */
		HttpSession session = request.getSession();

		if (session.getAttribute(ATT_SESSION_USER) == null) {
			/* Redirect into Login page*/
			request.setAttribute("restrict", "You need to be connected to access to this page. Please login");
			request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
		} else {
			/* Print restricted page */
			arg2.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
