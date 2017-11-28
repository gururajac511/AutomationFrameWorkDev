package com.incture.utility.actions;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class GenericActions {

	public static void sleep(long timeMs){
		try{
			Thread.sleep(timeMs);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * txt_enter() is used to enter text in the UI Field by passing below params 
	 * @param element  --> WebElement
	 * @param inputText --> Text that we are entering in the UI.
	 * @return
	 */
	public static boolean txt_enter(WebElement element,String inputText){
		element.sendKeys(inputText);
		element.sendKeys(Keys.TAB);
		sleep(1000);

		return !txt_getText(element).equals("");
		
	}
	/**
	 * txt_clear() is used to clear the input text UI field
	 * @param element
	 * @return
	 */
	public static boolean txt_clear(WebElement element)
	{
		element.clear();
		sleep(1000);
				
		return txt_getText(element).equals("");
	}
	/**
	 * 
	 * @param element
	 * @return
	 */
	public static String txt_getText(WebElement element){
		
		if(!element.getText().equals(""))
			return element.getText();
		else{
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
			String str="";
			element.click();
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			element.sendKeys(Keys.chord(Keys.CONTROL, "c"));
			element.click();
			try{
				str=(String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
			}catch (Exception e) {System.out.println(e.getMessage());}

			return str;			
		}
				
		//return element.getText();
	}
	/**
	 * 
	 * @param element
	 * @param attr
	 * @return
	 */
	public static String txt_getTextFromAttribute(WebElement element,String attr){
		return element.getAttribute(attr);
	}

	public static void btn_Click(WebElement element){

		element.click();	
		sleep(1000);

	}
	public static void btn_DoubbleClick(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.doubleClick(element);
		action.build();
		action.perform();		

	}
	public static boolean btn_isEnable(WebElement element){

		boolean flag=element.isEnabled();

		return flag;
	}

	/**
	 * drpdn_ul_SelectByVisibleText is used to select the dropdown values in UI & LI format
	 * @param driver
	 * @param downArrowButton eg:- By.className("sapMSltArrow")
	 * @param liList          eg:- By.xpath("//ul[@class='sapMSelectList']/li")
	 * @param textLov
	 * @return
	 */
	public static boolean drpdn_ul_SelectByVisibleText(WebDriver driver, By downArrowButton,By liList,String textLov){

		driver.findElement(downArrowButton).click();

		List<WebElement> options = driver.findElements(liList);

		for (WebElement opt : options) {
			System.out.println(opt.getText());
			if (opt.getText().equals(textLov)) {
				opt.click();
				return true;
			}
		}

		return false;
	}


	public static void drpdn_SelectByVisibleText(WebDriver driver,WebElement element,String textLov){
		try{
			//driver.findElement(By.xpath(".//*[@id='iStatusId-arrow']")).click();
			System.out.println(driver.findElement(By.xpath(".//*[@id='iStatusId-select']")).isDisplayed());
			Select dropdown = new Select(element);
			dropdown.selectByIndex(1);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}












}
