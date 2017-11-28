package com.incture.utility;


public class Constants {
	public static final String constantProp = "./src/com/incture/proj/objectRepository/Constant.properties";
	

	////public static final String xPathfile = "src/objectRepository/xpathLibrary.properties";
	
	public static final String reportName = PlugInFunctions.getConstantProperty("reportName");
	public static final String URL = PlugInFunctions.getConstantProperty("URL");
	public static final String ExcelPath = PlugInFunctions.getConstantProperty("ExcelPath");
	public static final String extentReportPath = PlugInFunctions.getConstantProperty("extentReportPath");//+Constants.reportName+ ".html";
	public static final String extentReportImgFolderName = PlugInFunctions.getConstantProperty("extentReportImgFolderName");
	public static final String dateFormat = PlugInFunctions.getConstantProperty("dateFormat");
	public static final String sheetName = PlugInFunctions.getConstantProperty("sheetName");
	public static final String browserName =  PlugInFunctions.getConstantProperty("browserName");
	public static final String chromeDriverExe =  PlugInFunctions.getConstantProperty("chromeDriverExe");
	public static final String xPathfile = PlugInFunctions.getConstantProperty("xPathfile");
}
