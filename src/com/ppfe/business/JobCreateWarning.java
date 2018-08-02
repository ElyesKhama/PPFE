package com.ppfe.business;

import java.util.ArrayList;

import javax.ejb.EJB;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.jobs.ee.ejb.EJB3InvokerJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ppfe.dao.PurchaseDAO;
import com.ppfe.entities.Purchase;
import com.ppfe.entities.Warning;
import com.ppfe.servlets.IndexServlet;

public class JobCreateWarning extends EJB3InvokerJob implements Job {
	private static final Logger logger = LoggerFactory.getLogger(IndexServlet.class);


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("--- Job Execution (Quartz) ---");
		try {
			ArrayList<Purchase> listPurchasesToday = new ArrayList<Purchase>();
			ArrayList<Purchase> listPurchasesYesterday = new ArrayList<Purchase>();
			ArrayList<Warning> listWarnings = new ArrayList<Warning>();

			Object[] args = initializationDataMapArgs("today");
			Class[] argsClass = initializationDataMapArgsClass(String.class);
			JobDataMap dataMap = initializationDataMap(arg0, "read", args, argsClass,"PurchaseDAO");

			super.execute(arg0);
			listPurchasesToday = (ArrayList<Purchase>) arg0.getResult();

			args = initializationDataMapArgs("yesterday");
			dataMap = initializationDataMap(arg0, "read", args, argsClass,"PurchaseDAO");
			super.execute(arg0);

			listPurchasesYesterday = (ArrayList<Purchase>) arg0.getResult();

			CheckWarning check = new CheckWarning(listPurchasesToday, listPurchasesYesterday);
			listWarnings = check.compareLists();

			int i;
			for (i = 0; i < listWarnings.size(); i++) {
				args = initializationDataMapArgs(listWarnings.get(i));
				argsClass = initializationDataMapArgsClass(Warning.class);
				dataMap = initializationDataMap(arg0, "create", args, argsClass,"WarningDAO");
				super.execute(arg0);
			}

		} catch (JobExecutionException e) {
			logger.error("Error during execution of Job");
		}
	}

	public static JobDataMap initializationDataMap(JobExecutionContext context, String nameMethod, Object[] args,
			Class[] argsClass,String nameEntityDAO) {
		JobDataMap dataMap = context.getMergedJobDataMap();
		dataMap.put(EJB_JNDI_NAME_KEY, "java:global/PPFE/"+nameEntityDAO+"!com.ppfe.dao."+nameEntityDAO);
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
