package com.ppfe.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ppfe.entities.User;
import com.ppfe.entities.VoucherType;
import com.ppfe.entities.Warning;

@Stateless
public class UserDAO {
	private static final Logger logger = LogManager.getLogger(WarningDAO.class);
	
	// Injection of manager who makes the connection with the database
	@PersistenceContext(unitName = "db_ppfe_pu_user")
	private EntityManager em;
	private static final String QUERY_READ_USER_USERNAME = "SELECT u FROM User u where u.username = :username";

	
	public User read(String username) throws DAOException {
		User user = null;
		Query query = null;
				
		try {
			query = em.createQuery(QUERY_READ_USER_USERNAME);
			query.setParameter("username", username);
			List results =  query.getResultList();
			if(!results.isEmpty()) {
				user = (User) results.get(0);
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return user;
	}
}
