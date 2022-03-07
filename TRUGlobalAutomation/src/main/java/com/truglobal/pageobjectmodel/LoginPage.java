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
public class LoginPage {
	private static final Logger logger = LogManager.getLogger(LoginPage.class);
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	Util util;
	SeleniumCommands selenium;

	public LoginPage(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("LoginPage.csv");
		selenium = new SeleniumCommands(driver, util);
	}

	public void enterUsername() {
		selenium.sendKeys("usernameInputField", service.getTestCaseDataColumn(0, "email"));
	}

	public void enterPassword() {
		selenium.sendKeys("passwordInputField", service.getTestCaseDataColumn(0, "password"));
	}

	public void clickSignInButton() throws InterruptedException {
		selenium.click("signInButton");
	}

}
