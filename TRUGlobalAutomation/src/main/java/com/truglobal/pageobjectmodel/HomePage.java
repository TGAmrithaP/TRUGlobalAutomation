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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.exceptions.CsvException;
import com.truglobal.seleniumcommands.SeleniumCommands;
import com.truglobal.util.ServiceLocator;
import com.truglobal.util.Util;

/**
 * @author Kumara Swamy
 *
 */
public class HomePage {
	private static final Logger logger = LogManager.getLogger(HomePage.class);
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	Util util;
	SeleniumCommands selenium;

	public HomePage(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("HomePage.csv");
		selenium = new SeleniumCommands(driver, util);
	}

	public void clickActionsMenu() throws InterruptedException {
		selenium.click("actionsMenu");
	}

	public void clickSetupAndMain() throws InterruptedException {
		selenium.click("setupAndMain");
	}

	public void clicksetupSelection() throws InterruptedException {
		selenium.click("setupSelection");
	}

	public void clickOrderManagement() throws InterruptedException {
		selenium.click("orderManagement");
	}

	public void enterSearchField() {
		selenium.sendKeys("searchField", service.getTestCaseDataColumn(0, "LookUpSearch"));
	}

	public void clickSearchButton() {
		selenium.click("searchButton");
	}

	public void clickManageOrderLookups() throws InterruptedException {
		selenium.click("manageOrderLookups");
	}

	public void clickmanageOrderLookupsMain() throws InterruptedException {
		selenium.click("manageOrderLookupsMain");
	}

	public void enterLookupType() {
		selenium.sendKeys("lookupType", service.getTestCaseDataColumn(0, "lookupType"));
	}

	public void clickOrderSearchButton() {
		selenium.click("orderSearchButton");
	}

	public void addNewLookupCode() {
		int rowCount = 0;
		while (true) {
			rowCount = util.findElements("lookupCodesRowCount").size();
			if (rowCount > 0) {
				break;
			}
		}
		selenium.click("addIcon");

		int previousRowCount = rowCount;
		while (rowCount <= previousRowCount) {
			rowCount = util.findElements("lookupCodesRowCount").size();
		}
	}

	public void enterLookupCode() {
		int rowCount = util.findElements("lookupCodesRowCount").size();
		selenium.sendKeys("lookupCodeInput", String.valueOf(rowCount + 1));
	}

	public void enterDisplaySequence() {
		int rowCount = util.findElements("lookupCodesRowCount").size();
		selenium.sendKeys("displaySequenceInput", String.valueOf(rowCount + 1));
	}

	public void clickStartDateCalendarIcon() {
		selenium.click("startDateIcon");
	}

	public void clickEndDateCalendarIcon() {
		selenium.click("endDateIcon");
	}

	public void selectDateOnCalendar() {
		selenium.click("calendar");
	}

	public void enterMeaning() {
		selenium.sendKeys("meaningInput", service.getTestCaseDataColumn(0, "meaning"));
	}

	public void clickSaveAndCloseButton() {
		selenium.click("saveAndCloseButton");
	}

	public void clickLookupCodeRow() {
		selenium.click("lookUpCodeRow");
	}

	public void clickDeleteIcon() {
		selenium.click("deleteIcon");
	}

	public void clickYesButton() {
		selenium.click("yesButton");
	}

}
