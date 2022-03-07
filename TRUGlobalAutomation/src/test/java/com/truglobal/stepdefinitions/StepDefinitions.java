/**
 * 
 */
package com.truglobal.stepdefinitions;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.opencsv.exceptions.CsvException;
import com.truglobal.pageobjectmodel.CheckoutPage;
import com.truglobal.pageobjectmodel.HomePage;
import com.truglobal.pageobjectmodel.LoginPage;
import com.truglobal.pageobjectmodel.QuaterlyPerformance;
import com.truglobal.pageobjectmodel.TaskToday;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.truglobal.util.ServiceLocator;

/**
 * @author Kumara Swamy
 *
 */
@Listeners({ ReportPortalTestNGListener.class })
public class StepDefinitions {
	private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

	ServiceLocator service = ServiceLocator.getInstance();
	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	CheckoutPage checkoutPage;
	TaskToday task;
	QuaterlyPerformance QUP;

	@Given("^launch the browser$")
	public void user_is_on_Home_Page() throws IOException, CsvException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\TG1698\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		logger.info("Starting ChromeDriver");
		driver = new ChromeDriver();
		logger.info("ChromeDriver Started Successfully");
		logger.info("Start of Window Maximize");
		driver.manage().window().maximize();
		logger.info("End of Window Maximize");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		checkoutPage = new CheckoutPage(driver);
		task = new TaskToday(driver);
		QUP = new QuaterlyPerformance(driver);

	}

	@Given("^The Aplication is loaded$")
	public void The_apllication_is_loaded() {
		logger.info("Start of Open URL");
		driver.get("https://truglobalsoftware.my.salesforce.com/");
		logger.info("End of Open URL");
	}

	@When("^Enter the user login credentials$")
	public void The_user_enters_the_valid_user_name() throws Throwable {
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

	@And("^Click on App launcher and search for PROJECTS in the items list$")
	public void Click_on_App_launcher() throws Throwable {
		logger.info("Start click on applauncher");
		homePage.clickonAppLauncher();
		logger.info("End on applauncher");

	}

	@And("^Click on the PROJECTS item$")
	public void Click_on_the_PROJECTS_item() throws Throwable {
		logger.info("Start click on projectitem");

		homePage.clickonProject();
		logger.info("End on projectitem");

	}

	@Then("^Verify Project name on home page$")
	public void Verify_Project_name_on_home_page() throws Throwable {
		String actualdata = "Sales";
		logger.info("verify project name");
		Assert.assertEquals(true, homePage.verifyPagetitle());
		logger.info("End on verify project name");
	}

	@And("^User navigate to Recent contacts section$")
	public void User_navigate_to_Recent_contacts_section() throws Throwable {
		logger.info("User navigate to Recent contacts section");
		homePage.recentContact();
		logger.info("End on User navigate to Recent contacts section ");
	}

	@Then("^Verify Recent contact list$")
	public void Verify_Recent_contact_list() throws Throwable {
		logger.info("Verify Recent contact list");
		homePage.verifyrecconli();
		// Assert.assertEquals(true, homePage.verifyPagetitle());
		logger.info("End on Verify Recent contact list ");

	}

	@When("User navigate to Today's Task Section.")
	public void user_navigate_to_today_s_task_section() throws InterruptedException {
		logger.info("User navigate to Today's Task Section.");
		task.clickOnTodaysTask();
	}

	@When("User verify the Closed , Open, Goals details of Quarterly Performance")
	public void user_verify_the_closed_open_goals_details_of_quarterly_performance() throws InterruptedException {
		logger.info("User verify the Closed , Open, Goals details of Quarterly Performance");
		homePage.clickonHomePage();
		QUP.getClickOnQuaterlyPerformance();

	}

	@When("Get title of Salesforce Account page")
	public void getSalesForceAccountTitle() throws InterruptedException {
		logger.info("Get the Salesforce Account page Title");
		homePage.clickOnSalesforceAccount();
		System.out.println(driver.getTitle());
		homePage.verifyAccount();
		homePage.verifyGlobal();
	}

	@When("^testcase is completed$")
	public void teardown() {
		driver.quit();
	}
}
