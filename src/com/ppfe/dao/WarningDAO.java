package com.ppfe.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ppfe.entities.Purchase;
import com.ppfe.entities.Warning;

@Stateless
public class WarningDAO {
	private static final Logger logger = LogManager.getLogger(WarningDAO.class);

	private static final String QUERY_READ_WARNING = "SELECT w FROM Warning w";

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

	public ArrayList<Warning> read() throws DAOException {
		ArrayList<Warning> listWarnings = new ArrayList<Warning>();

		try {
			logger.info("--- Read Warning into Database ---");
			Query query = null;
			query = em.createQuery(QUERY_READ_WARNING);
			listWarnings = (ArrayList<Warning>) query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return listWarnings;
	}
}