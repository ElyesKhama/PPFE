package com.ppfe.business;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.jobs.ee.ejb.EJB3InvokerJob;

import com.ppfe.dao.WarningDAO;
import com.ppfe.entities.Purchase;
import com.ppfe.entities.Warning;
import com.ppfe.servlets.IndexServlet;

public class JobCreateWarning extends EJB3InvokerJob implements Job {
	private static final Logger logger = LogManager.getLogger(JobCreateWarning.class);

	//method called when the trigger is fired
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("--- Job Execution (Quartz) ---");
		ArrayList<Purchase> listPurchasesToday = new ArrayList<Purchase>();
		ArrayList<Purchase> listPurchasesYesterday = new ArrayList<Purchase>();
		ArrayList<Warning> listWarnings = new ArrayList<Warning>();
		
		try {
			//purchaseDao.read("today")
			executeMethod(arg0, "today", String.class, "read", "PurchaseDAO");
			listPurchasesToday = (ArrayList<Purchase>) arg0.getResult();
			//purchaseDao.read("yesterday")
			executeMethod(arg0, "yesterday", String.class, "read", "PurchaseDAO");
			listPurchasesYesterday = (ArrayList<Purchase>) arg0.getResult();

			CheckWarning check = new CheckWarning(listPurchasesToday, listPurchasesYesterday);
			listWarnings = check.comparePurchases();

			createWarnings(arg0,listWarnings);
			SendMail.send(listWarnings);
			
		} catch (JobExecutionException e) {
			logger.error("Error during execution of Job : " + e.getMessage());
		}
	}
	
	public void createWarnings(JobExecutionContext context, ArrayList<Warning> listWarnings) throws JobExecutionException {
		int i;
		for (i = 0; i < listWarnings.size(); i++) {
			executeMethod(context, listWarnings.get(i), Warning.class, "create", "WarningDAO");
		}
	}

	// TODO: ADD SOME GENERICITY
	public void executeMethod(JobExecutionContext context, Object arg, Class argClass, String nameMethod,
			String nameEntityDAO) throws JobExecutionException {
		Object[] args = initializationDataMapArgs(arg);
		Class[] argsClass = initializationDataMapArgsClass(argClass);
		JobDataMap dataMap = initializationDataMap(context, nameMethod, args, argsClass, nameEntityDAO);
		super.execute(context);
	}

	public void executeMethod(JobExecutionContext context, String nameMethod, String nameEntityDAO)
			throws JobExecutionException {
		JobDataMap dataMap = initializationDataMap(context, nameMethod, nameEntityDAO);
		super.execute(context);
	}

	public static JobDataMap initializationDataMap(JobExecutionContext context, String nameMethod,
			String nameEntityDAO) {
		JobDataMap dataMap = context.getMergedJobDataMap();
		dataMap.put(EJB_JNDI_NAME_KEY, "java:global/PPFE/" + nameEntityDAO + "!com.ppfe.dao." + nameEntityDAO);
		dataMap.put(EJB_METHOD_KEY, nameMethod);
		dataMap.put(EJB_ARGS_KEY, null);
		dataMap.put(EJB_ARG_TYPES_KEY, null);
		return dataMap;
	}

	public static JobDataMap initializationDataMap(JobExecutionContext context, String nameMethod, Object[] args,
			Class[] argsClass, String nameEntityDAO) {
		JobDataMap dataMap = context.getMergedJobDataMap();
		dataMap.put(EJB_JNDI_NAME_KEY, "java:global/PPFE/" + nameEntityDAO + "!com.ppfe.dao." + nameEntityDAO);
		dataMap.put(EJB_METHOD_KEY, nameMethod);
		dataMap.put(EJB_ARGS_KEY, args);
		dataMap.put(EJB_ARG_TYPES_KEY, argsClass);
		return dataMap;
	}

	public static Object[] initializationDataMapArgs(Object... objects) {
		Object[] args = new Object[objects.length];
		int i;
		for (i = 0; i < objects.length; i++) {
			args[i] = objects[i];
		}
		return args;
	}

	public static Class[] initializationDataMapArgsClass(Class... objects) {
		Class[] args = new Class[objects.length];
		int i;
		for (i = 0; i < objects.length; i++) {
			args[i] = objects[i];
		}
		return args;
	}

}
