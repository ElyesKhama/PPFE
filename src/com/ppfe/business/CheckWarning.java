package com.ppfe.business;

import java.util.ArrayList;

import javax.ejb.EJB;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.jobs.ee.ejb.EJB3InvokerJob;

import com.ppfe.dao.WarningDAO;
import com.ppfe.entities.Purchase;
import com.ppfe.entities.Warning;

public class CheckWarning {

	private ArrayList<Purchase> listPurchasesToday = new ArrayList<Purchase>();
	private ArrayList<Purchase> listPurchasesYesterday = new ArrayList<Purchase>();
	private ArrayList<Warning> listWarnings = new ArrayList<Warning>();
	private JobExecutionContext context;

	private static final int DIFF_LOW = 0;
	private static final int DIFF_MEDIUM = -50;
	private static final int DIFF_HIGH = -100;
	private static final String PRIORITY_LOW = "low";
	private static final String PRIORITY_MEDIUM = "medium";
	private static final String PRIORITY_HIGH = "high";

	@EJB
	private WarningDAO warningDao;

	public CheckWarning(ArrayList<Purchase> listPurchasesToday, ArrayList<Purchase> listPurchasesYesterday) {
		this.listPurchasesToday = listPurchasesToday;
		this.listPurchasesYesterday = listPurchasesYesterday;
		this.context = context;
	}

	public ArrayList<Warning> compareLists() {
		System.out.println("LES PURCHASES D'AUJOURDHUI SONT : \n" + listPurchasesToday.toString());
		System.out.println("LES PURCHASES D'HIER SONT : \n" + listPurchasesYesterday.toString());

		int i;
		for (i = 0; i < listPurchasesToday.size(); i++) {
			calculateDifference(listPurchasesToday.get(i), listPurchasesYesterday.get(i));
		}
		return listWarnings;
	}

	public void calculateDifference(Purchase pToday, Purchase pYesterday) {
		int difference;
		String priority = null;
		boolean war = false;
		difference = pToday.getCountTotal() - pYesterday.getCountTotal();
		
		if (difference < DIFF_HIGH) {
			priority = PRIORITY_HIGH;
			war = true;
			System.out.println("DIFF INF A 100 ICI ENTRE : " + pToday.toString() + "ET :" + pYesterday.toString());
		} else if (difference < DIFF_MEDIUM) {
			priority = PRIORITY_MEDIUM;
			war = true;
			System.out.println("DIFF INF A 50 ICI ENTRE : " + pToday.toString() + "ET :" + pYesterday.toString());
		} else if (difference < DIFF_LOW) {
			priority = PRIORITY_LOW;
			war = true;
			System.out.println("DIFF INF A 0 ICI ENTRE : " + pToday.toString() + "ET :" + pYesterday.toString());
		}
		if(war == true) {
			addWarning(priority, difference, pToday.getId());
		}
	}

	public void addWarning(String priority, int countDifference, Long idPurchase) {
		Warning warning = new Warning();
		warning.setPriority(priority);
		warning.setCountDifference(countDifference);
		warning.setIdPurchase(idPurchase);
		listWarnings.add(warning);
	}
}
