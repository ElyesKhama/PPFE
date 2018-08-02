package com.ppfe.business;

import java.util.ArrayList;

import javax.ejb.EJB;

import com.ppfe.dao.WarningDAO;
import com.ppfe.entities.Purchase;
import com.ppfe.entities.Warning;

public class CheckWarning {

	private ArrayList<Purchase> listPurchasesToday = new ArrayList<Purchase>();
	private ArrayList<Purchase> listPurchasesYesterday = new ArrayList<Purchase>();
	
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
	}

	public void compareLists() {
		System.out.println("LES PURCHASES D'AUJOURDHUI SONT : \n"+listPurchasesToday.toString());
		System.out.println("LES PURCHASES D'HIER SONT : \n"+listPurchasesYesterday.toString());

		int i;
		for(i=0;i<listPurchasesToday.size();i++) {
			calculateDifference(listPurchasesToday.get(i),listPurchasesYesterday.get(i));
		}
	}
	
	public void calculateDifference(Purchase pToday, Purchase pYesterday) {
		int difference;
		String priority = null;
		difference = pToday.getCountTotal() - pYesterday.getCountTotal();
		
		if(difference < DIFF_HIGH) {
			priority = PRIORITY_HIGH;
			System.out.println("DIFF INF A 100 ICI ENTRE : "+pToday.toString() +"ET :" + pYesterday.toString());
		}
		else if(difference < DIFF_MEDIUM) {
			priority = PRIORITY_MEDIUM;
			System.out.println("DIFF INF A 50 ICI ENTRE : "+pToday.toString() +"ET :" + pYesterday.toString());
		}
		else if(difference < DIFF_LOW) {
			priority = PRIORITY_LOW;
			System.out.println("DIFF INF A 0 ICI ENTRE : "+pToday.toString() +"ET :" + pYesterday.toString());
		}
		
		persistWarning(priority,difference,pToday.getId());
	}
	
	public void persistWarning(String priority, int countDifference, Long idPurchase) {
		Warning warning = new Warning();
		warning.setPriority(priority);
		warning.setCountDifference(countDifference);
		warning.setIdPurchase(idPurchase);
		
		warningDao.create(warning);
	}
}
