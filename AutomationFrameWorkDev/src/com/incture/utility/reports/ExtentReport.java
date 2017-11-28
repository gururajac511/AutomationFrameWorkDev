package com.incture.utility.reports;

import java.util.HashMap;
import java.util.Map;

import com.incture.utility.Constants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class ExtentReport {

	public static Map extentTestMap = new HashMap();
	public static ExtentReports	report=null;


	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		getReporter().endTest((ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId())));
		getReporter().flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		return startTest(testName, "");
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		//	extent = ExtentManager.getReporter();
		ExtentTest test = getReporter().startTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

		return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
		//return test;
	}
	public static ExtentReports getReporter(){
		if(report==null)
		report=new ExtentReports(Constants.extentReportPath+Constants.reportName,true);
		return report;
	}

}
