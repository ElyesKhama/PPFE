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

import com.ppfe.entities.Purchase;

@Stateless
public class PurchaseDAO {

	// Injection of manager who makes the connection with the database
	@PersistenceContext(unitName = "db_ppfe_pu_purchase")
	private EntityManager em;

	// constants
	private static final String QUERY_READ_ALL_PURCHASES = "SELECT u FROM Purchase u";
	private static final String QUERY_READ_DAY_PURCHASES = "SELECT u FROM Purchase u WHERE u.dateDay = :dateDay";
	private static final String QUERY_READ_DATE_VOUCHER_PURCHASE = "SELECT u FROM Purchase u WHERE u.dateDay = :dateDay and voucherType.id = :id";

	private static final Logger logger = LogManager.getLogger(PurchaseDAO.class);

	public void create(Purchase purchase) throws DAOException {
		try {
			logger.info("--- Persist Purchase into Database ---");
			em.persist(purchase);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	// read purchases into database for a specific date and id
	public Purchase read(Date dateDay, Long idVoucher) {
		Purchase purchase = null;
		Query query = null;
		try {
			query = em.createQuery(QUERY_READ_DATE_VOUCHER_PURCHASE);
			query.setParameter("dateDay", dateDay);
			query.setParameter("id", idVoucher);
			purchase = (Purchase) query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return purchase;
	}

	public ArrayList<Purchase> read(String choice) throws DAOException {
		logger.info("--- Read Purchases from Database ---");

		ArrayList<Purchase> listPurchases = new ArrayList<Purchase>();
		Query query = null;

		// initialization of Calendar
		Calendar calendar = Calendar.getInstance();
		// Instantiation with date of Today
		java.util.Date dateUtil = calendar.getTime();
		// Conversion into java.sql.Date instead of java.util.Date (needed for query)
		Date dateSql = new Date(dateUtil.getTime());

		// TODO: GetTimeInMillis ??
		try {
			switch (choice) {
			case "all":
				query = em.createQuery(QUERY_READ_ALL_PURCHASES);
				break;
			case "today":
				query = em.createQuery(QUERY_READ_DAY_PURCHASES);
				query.setParameter("dateDay", dateSql);
				break;
			case "yesterday":
				// Substraction of 1 Day to obtain the Date of yesterday
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				dateUtil = calendar.getTime();
				// Conversion into java.sql.Date instead of java.util.Date (needed for query)
				dateSql = new Date(dateUtil.getTime());
				query = em.createQuery(QUERY_READ_DAY_PURCHASES);
				query.setParameter("dateDay", dateSql);
				break;
			default:
				logger.error("Wrong argument to method read !");
				break;
			}
			listPurchases = (ArrayList<Purchase>) query.getResultList();
			return listPurchases;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
