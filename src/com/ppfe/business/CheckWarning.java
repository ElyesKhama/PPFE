package com.ppfe.business;

import java.sql.Date;
import java.util.ArrayList;

import javax.ejb.EJB;

import com.ppfe.dao.WarningDAO;
import com.ppfe.entities.Purchase;
import com.ppfe.entities.Warning;

public class CheckWarning {

	// attributes
	private ArrayList<Purchase> listPurchasesToday = new ArrayList<Purchase>();
	private ArrayList<Purchase> listPurchasesYesterday = new ArrayList<Purchase>();
	private ArrayList<Warning> listWarnings = new ArrayList<Warning>();

	// constants
	// Levels of warning in terms of difference
	private static final int DIFF_LOW = 0;
	private static final int DIFF_MEDIUM = -50;
	private static final int DIFF_HIGH = -100;
	private static final String PRIORITY_LOW = "low";
	private static final String PRIORITY_MEDIUM = "medium";
	private static final String PRIORITY_HIGH = "high";

	// injection of the EJB to have access to method interacting with DB
	@EJB
	private WarningDAO warningDao;

	// constructor. Object initialized with the lists of warning for today and
	// yesterday
	public CheckWarning(ArrayList<Purchase> listPurchasesToday, ArrayList<Purchase> listPurchasesYesterday) {
		this.listPurchasesToday = listPurchasesToday;
		this.listPurchasesYesterday = listPurchasesYesterday;
	}

	// method which compare today's and yesterday's purchases
	public ArrayList<Warning> comparePurchases() {
		int i;
		for (i = 0; i < listPurchasesToday.size(); i++) {
			calculateDifference(listPurchasesToday.get(i), listPurchasesYesterday.get(i));
		}
		return listWarnings;
	}

	// method which calculate the difference between the count's purchases
	public void calculateDifference(Purchase pToday, Purchase pYesterday) {
		int difference;
		String priority = null;
		boolean war = false;
		difference = pToday.getCountTotal() - pYesterday.getCountTotal();

		// compare the difference and the warning levels
		if (difference <= DIFF_HIGH) {
			priority = PRIORITY_HIGH;
			war = true;
		} else if (difference <= DIFF_MEDIUM) {
			priority = PRIORITY_MEDIUM;
			war = true;
		} else if (difference < DIFF_LOW) {
			priority = PRIORITY_LOW;
			war = true;
		}
		if (war == true) { // there is a warning to create
			addWarning(priority, difference, pToday);
		}
	}

	// method which add warnings to the list
	public void addWarning(String priority, int countDifference, Purchase purchase) {
		Warning warning = new Warning(); // create the warning
		warning.setPriority(priority); // set all attributes
		warning.setCountDifference(countDifference);
		purchase.setId(purchase.getId());
		warning.setPurchase(purchase);
		warning.setDateDay(new Date(System.currentTimeMillis())); // today's date
		listWarnings.add(warning); // add the warning to the list
	}
}
