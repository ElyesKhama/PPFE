package com.ppfe.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ppfe.entities.Warning;

@Stateless
public class WarningDAO {
	// constants
	private static final Logger logger = LogManager.getLogger(WarningDAO.class);

	private static final String QUERY_READ_WARNING = "SELECT w FROM Warning w";
	private static final String QUERY_READ_DAY_WARNING = "SELECT w FROM Warning w WHERE w.dateDay >= :dateDay";
	private static final String QUERY_COUNT_WARNING = "SELECT COUNT(w) FROM Warning w WHERE w.dateDay = :dateDay";
	private static final String QUERY_COUNT_WARNING_NB_DAYS = "SELECT COUNT(w) FROM Warning w WHERE w.dateDay >= :dateDay";
	private static final String QUERY_READ_WARNING_ID = "SELECT w FROM Warning w WHERE w.id = :id";

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

	// read THE warning into the databse with the specific ID
	public Warning read(Long id) throws DAOException {
		Warning warning = null;
		Query query = null;
		try {
			query = em.createQuery(QUERY_READ_WARNING_ID);
			query.setParameter("id", id);

			warning = (Warning) query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return warning;
	}

	// read all warnings into the database
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

	// read all warnings for a period of a specific number of days (used for the
	// number of warnings today/week/month)
	public ArrayList<Warning> readDay(int nbDays) throws DAOException {
		logger.info("--- Read Warning into Database ---");

		ArrayList<Warning> listWarnings = new ArrayList<Warning>();
		Query query = null;

		// initialization of Calendar
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -nbDays);
		Date dateParam = new Date(calendar.getTime().getTime());

		try {
			query = em.createQuery(QUERY_READ_DAY_WARNING);
			query.setParameter("dateDay", dateParam);
			listWarnings = (ArrayList<Warning>) query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return listWarnings;
	}

	// method which count the number of warnings for the date_day : curdate - nbdays
	public Long count(int nbDays) throws DAOException {
		return utilCount(QUERY_COUNT_WARNING, nbDays);
	}

	// method which count the total number of warnings during the last nbDays days
	public Long countTotalPreviousDays(int nbDays) {
		return utilCount(QUERY_COUNT_WARNING_NB_DAYS, nbDays);
	}

	public Long utilCount(String queryString, int nbDays) {
		logger.info("--- Count Warning into Database ---");
		Long count;
		Query query = null;

		// initialization of Calendar
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -nbDays);
		Date dateParam = new Date(calendar.getTime().getTime());

		try {
			query = em.createQuery(queryString);
			query.setParameter("dateDay", dateParam);
			count = (Long) query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return count;
	}

}