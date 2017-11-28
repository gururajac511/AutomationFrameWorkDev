package com.incture.utility.reports;

import org.openqa.selenium.WebDriver;

import com.incture.utility.PlugInFunctions;
import com.incture.utility.openBrowser;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	WebDriver driver=null;
	public   void pass(String stepName,String details,boolean screenShotCapture){
		if(screenShotCapture)
		{
			PlugInFunctions gf=new PlugInFunctions();
			String img=gf.takeScreenShot(driver);
			String logImg=ExtentReport.getTest().addScreenCapture(img);

			ExtentReport.getTest().log(LogStatus.PASS, stepName, details+"\n"+logImg);

			gf=null;
		}
		else{
			ExtentReport.getTest().log(LogStatus.PASS, stepName, details);
		}

	}
	public   void info(String stepName,String details,boolean screenShotCapture){
		if(screenShotCapture)
		{
			PlugInFunctions gf=new PlugInFunctions();
			String img=gf.takeScreenShot(driver);
			String logImg=ExtentReport.getTest().addScreenCapture(img);

			ExtentReport.getTest().log(LogStatus.INFO, stepName, details+"\n"+logImg);

			gf=null;
		}
		else{
			ExtentReport.getTest().log(LogStatus.INFO, stepName, details);
		}

	}

	public  void fail(String stepName,String details,boolean screenShotCapture){
		if(screenShotCapture)
		{
			PlugInFunctions gf=new PlugInFunctions();
			String img=gf.takeScreenShot(driver);
			String logImg=ExtentReport.getTest().addScreenCapture(img);

			ExtentReport.getTest().log(LogStatus.FAIL, stepName, details+"\n"+logImg);

			gf=null;
		}
		else{
			ExtentReport.getTest().log(LogStatus.FAIL, stepName, details);
		}

	}

	public Report(WebDriver driver){
		this.driver=driver;
	}

}
