package com.incture.utility.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.incture.utility.PlugInFunctions;
import com.incture.utility.reports.ExtentReport;

public class TestListeners  implements ITestListener{

	public void onTestStart(ITestResult result) {
		System.out.println("--> lis onTestStart()" +  getTestMethodName(result) + " start");
		ExtentReport.startTest(getTestMethodName(result)).assignCategory(PlugInFunctions.getDateNow("dd-MM-yyyy"));
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("--> lis onTestSuccess() " + getTestMethodName(result) + " success");
		ExtentReport.endTest();
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("--> lis  onTestFailure()" + getTestMethodName(result) + " failure");
		ExtentReport.endTest();
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("--> lis  onTestSkipped()" + getTestMethodName(result) + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("--> lis onTestFailedButWithinSuccessPercentage() " + getTestMethodName(result));
	}

	
	public void onStart(ITestContext context) {
		System.out.println("--> lis  onStart()" + context.getName());
	}

	public void onFinish(ITestContext context) {
		System.out.println("--> lis  onFinish()" + context.getName());
	}
	
	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}

}
