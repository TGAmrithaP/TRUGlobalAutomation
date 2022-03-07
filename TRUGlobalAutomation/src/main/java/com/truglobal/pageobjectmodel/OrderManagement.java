/**
 * 
 */
package com.truglobal.pageobjectmodel;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.opencsv.exceptions.CsvException;
import com.truglobal.seleniumcommands.SeleniumCommands;
import com.truglobal.util.ServiceLocator;
import com.truglobal.util.Util;

/**
 * @author Kumara Swamy
 *
 */
public class OrderManagement {
	private static final Logger logger = LogManager.getLogger(OrderManagement.class);
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	Util util;
	SeleniumCommands selenium;

	public OrderManagement(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("OrderManagementNavigation.csv");
		selenium = new SeleniumCommands(driver, util);
	}

	public void clickOrderManagementNav() {
		selenium.click("navorderManagement");
	}

	public void clickOrderManagementTab() {
		selenium.click("navorderManagementTab");
	}

	public void clickCreateButton() {
		selenium.click("createButton");
	}
}
