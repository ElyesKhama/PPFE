package com.ppfe.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ppfe.entities.Warning;

@Stateless
public class WarningDAO {
	// Injection of manager who makes the connection with the database

	@PersistenceContext(unitName = "db_ppfe_pu_warning")
	private EntityManager em;

	public void create(Warning warning) throws DAOException {
		try {
			em.persist(warning);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}