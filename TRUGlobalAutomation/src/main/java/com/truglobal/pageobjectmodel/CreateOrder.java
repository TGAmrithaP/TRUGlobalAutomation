/**
 * 
 */
package com.truglobal.pageobjectmodel;

import java.io.IOException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
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
public class CreateOrder {
	private static final Logger logger = LogManager.getLogger(CreateOrder.class);
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	Util util;
	SeleniumCommands selenium;

	public CreateOrder(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("CreateOrder.csv");
		selenium = new SeleniumCommands(driver, util);
	}

	public void enterCustomerField() {
		selenium.sendKeys("customer", service.getTestCaseDataColumn(0, "customer"));
	}

	public void clickCustomerFieldSuggestion() {
		selenium.click("customerInputSuggestionBox");
	}

	public void clickContactMethod() {
		selenium.click("contactMethod");
	}

	public void clickContactMethodSuggestion() {
		selenium.click("emailSuggestion");
	}

	public void assertBillToCustomerText() throws InterruptedException {
		selenium.waitForAttribute("billToAccount", "value", service.getTestCaseDataColumn(0, "billToCustomerId"));
	}

	public void enterPurchaseOrder() throws InterruptedException {
		Random random = new Random();
		int number = random.nextInt(999);
		String poNumber = "PO" + number + "N";

		selenium.sendKeys("purchaseOrder", poNumber);
	}

	public void enterOrderType() {
		selenium.sendKeys("orderType", service.getTestCaseDataColumn(0, "orderType"));
	}

	public void clickOrderTypeSuggestion() {
		selenium.click("orderTypeSuggestion");
	}

	public void enterSelectItemsSearchbar() {
		selenium.sendKeys("selectItemInputBox", service.getTestCaseDataColumn(0, "selectItem"));
	}

	public void clickSelectItemsSearchbarSuggestion() {
		selenium.click("selectItemInputBoxSuggestion");
	}

	public void clickAddButton() {
		selenium.click("addButton");
	}

	public void clickSaveButton() {
		selenium.click("saveButton");
	}

	public void clickSubmitButton() {
		selenium.click("submitButton");
	}

	public void verifyWarningMessage() {
		String text = selenium.getText("warningMessage");
		Assert.assertEquals(text, service.getTestCaseDataColumn(0, "warningMessage"));
	}

	public void clickSubmitOrderButton() throws InterruptedException {
		selenium.click("submitOrderButton");
	}

	public void verifyOrderConfirmationMessage() {
		String confirmationText = selenium.getText("orderConfirmation");
		Assert.assertTrue(confirmationText.startsWith("Sales order ") && confirmationText.endsWith("was submitted."));
	}

	public void clickOKButton() {
		selenium.click("okButton");
	}
}
