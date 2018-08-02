package com.ppfe.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ppfe.entities.Warning;

@Stateless
public class WarningDAO {
	private static final Logger logger = LoggerFactory.getLogger(PurchaseDAO.class);

	
	// Injection of manager who makes the connection with the database
	@PersistenceContext(unitName = "db_ppfe_pu_warning")
	private EntityManager em;

	public void create(Warning warning) throws DAOException {
		try {
			logger.info("--- Persist Warning into Database ---");
			em.persist(warning);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}