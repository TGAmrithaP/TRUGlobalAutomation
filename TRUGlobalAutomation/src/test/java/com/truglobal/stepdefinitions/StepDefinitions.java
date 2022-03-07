/**
 * 
 */
package com.truglobal.stepdefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.opencsv.exceptions.CsvException;
import com.truglobal.pageobjectmodel.CreateOrder;
import com.truglobal.pageobjectmodel.LoginPage;
import com.truglobal.pageobjectmodel.OrderManagement;
import com.truglobal.util.ServiceLocator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

/**
 * @author Kumara Swamy
 *
 */
@Listeners({ ReportPortalTestNGListener.class })
public class StepDefinitions {
	private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

	ServiceLocator service = ServiceLocator.getInstance();
	WebDriver driver;
	LoginPage loginPage;
	OrderManagement orderManagement;
	CreateOrder createOrder;

	@SuppressWarnings("static-access")
	@Given("^launch the browser$")
	public void launchBrowser() throws IOException, CsvException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/TG1698/Downloads/chromedriver_win32 (1)/chromedriver.exe");
		logger.info("Starting ChromeDriver");
		driver = new ChromeDriver();
		logger.info("ChromeDriver Started Successfully");
		logger.info("Start of Window Maximize");
		driver.manage().window().maximize();
		logger.info("End of Window Maximize");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		loginPage = new LoginPage(driver);
		orderManagement = new OrderManagement(driver);
		createOrder = new CreateOrder(driver);
	}

	@Given("^The Aplication is loaded$")
	public void loadApplication() {
		logger.info("Start of Open URL");
		driver.get("https://egxf-dev2.fa.us2.oraclecloud.com/fscmUI/faces/FuseWelcome?_adf.ctrl-state=i2uz840og_156");
		logger.info("End of Open URL");
	}

	@When("^Enter the user login credentials$")
	public void authenticate() throws Throwable {
		logger.info("Start of enter Username");
		loginPage.enterUsername();
		logger.info("End of enter Username");
		Thread.sleep(2000);
		logger.info("Start of enter Password");
		loginPage.enterPassword();
		logger.info("End of enter Password");
		Thread.sleep(2000);
		logger.info("Start of click SignIn button");
		loginPage.clickSignInButton();
		logger.info("End of click SignIn button");
	}

	@When("^User navigates to OrderManagement and Click Create Order$")
	public void navigateToOrderManagement() throws Throwable {
		logger.info("Start of navigate Order Management");
		orderManagement.clickOrderManagementNav();
		orderManagement.clickOrderManagementTab();
		orderManagement.clickCreateButton();
		logger.info("End of navigate Order Management");
	}

	@When("^User enters new order information$")
	public void fillNewOrderForm() throws Throwable {
		logger.info("Start of enter data into create order form");
		createOrder.enterCustomerField();
		createOrder.clickCustomerFieldSuggestion();
		createOrder.assertBillToCustomerText();
		createOrder.clickContactMethod();
		createOrder.clickContactMethodSuggestion();
		createOrder.enterPurchaseOrder();
		createOrder.enterOrderType();
		createOrder.clickOrderTypeSuggestion();
		logger.info("End of enter data into create order form");
	}

	@When("^User searches an Item$")
	public void searchItems() throws Throwable {
		logger.info("Start of enter data into create order form");
		createOrder.enterSelectItemsSearchbar();
		createOrder.clickSelectItemsSearchbarSuggestion();
		createOrder.clickAddButton();
		Thread.sleep(5000);
		createOrder.clickSaveButton();
		Thread.sleep(5000);
		createOrder.clickSubmitButton();
		logger.info("End of enter data into create order form");
	}

	@When("^Verify Warning and Order Confirmation message$")
	public void verifyWarningAndOrderConfirmation() throws Throwable {
		logger.info("Start of verify messages");
//		createOrder.selectWarningFrame();
		createOrder.verifyWarningMessage();
		createOrder.clickSubmitOrderButton();
		Thread.sleep(5000);
		createOrder.verifyOrderConfirmationMessage();
		createOrder.clickOKButton();
		logger.info("Start of verify messages");
	}

	@When("^testcase is completed$")
	public void teardown() {
		driver.quit();
	}
}
