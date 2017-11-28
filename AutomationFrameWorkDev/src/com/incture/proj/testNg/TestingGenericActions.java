package com.incture.proj.testNg;

import java.awt.HeadlessException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.incture.utility.Constants;
import com.incture.utility.openBrowser;
import com.incture.utility.actions.GenericActions;
import com.incture.utility.reports.Report;

public class TestingGenericActions {

	WebDriver driver =null;

	@Test
	public void testingGenericMethods() throws InterruptedException, HeadlessException, UnsupportedFlavorException, IOException{
		openBrowser op=new openBrowser();
		driver=op.LaunchLocalBrowser("chrome", Constants.URL);
		m1(driver);

	}

	public void m1(WebDriver driver) throws HeadlessException, UnsupportedFlavorException, IOException{
		
		Report report=new Report(driver);

		String materialXpath=".//*[contains(text(),'Material No./Descri')]//parent::div//following::input[1]";

		boolean flag=GenericActions.txt_enter(driver.findElement(By.xpath(materialXpath)), "BEAT-5286");
		
		report.pass("Material Name ", "material name detialys BEAT-5286 ", true);
		

		GenericActions.txt_clear(driver.findElement(By.xpath(materialXpath)));

		String lov=".//*[@id='iStatusId-select']";//".//*[contains(text(),'Status:')]//parent::div//following::select[1]";

		GenericActions.drpdn_ul_SelectByVisibleText(driver,By.className("sapMSltArrow"),By.xpath("//ul[@class='sapMSelectList']/li"), "Update Pending");
		report.pass("sapMSltArrow", "sapMSltArrow Update Pending ", true);
		report.fail("sapMSltArrow", "sapMSltArrow Update Pending ", true);
	
		GenericActions.btn_Click(driver.findElement(By.xpath(".//*[@class='inctureDialogBtn sapMBarChild sapMBtn sapMBtnBase sapUiSizeCompact sapUiSmallMarginBegin']/div/.//*[contains(text(),'Search')]")));
		report.info("sapMSltArrow", "sapMSltArrow Update Pending ", true);
		GenericActions.sleep(4000);

	}


}
