/**
 * 
 */
package com.truglobal.pageobjectmodel;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.opencsv.exceptions.CsvException;

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

	public LoginPage(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("LoginPage.csv");
	}

	public void enterUsername() throws InterruptedException {
		WebElement usernameInputField = util.findElement("usernameInputField");
		usernameInputField.sendKeys(service.getTestCaseDataColumn(0, "email"));
	}

	public void enterPassword() throws InterruptedException {
		WebElement passwordInputField = util.findElement("passwordInputField");
		passwordInputField.sendKeys(service.getTestCaseDataColumn(0, "password"));
	}

	public void clickSignInButton() throws InterruptedException {
		WebElement signInButton = util.findElement("signInButton");
		signInButton.click();
	}

}
