/**
 * 
 */
package StepDefinitions;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.opencsv.exceptions.CsvException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.CheckoutPage;
import pom.HomePage;
import pom.LoginPage;
import pom.ProductDescriptionPage;
import pom.ProductListingPage;
import util.ServiceLocator;

/**
 * @author Kumara Swamy
 *
 */
@Listeners({ ReportPortalTestNGListener.class })
public class StepDefinitions {
	private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

	ServiceLocator service = ServiceLocator.getInstance();
	WebDriver driver;
	String product = service.getTestCaseDataColumn(0, "productName");
	HomePage homePage;
	LoginPage loginPage;
	ProductListingPage plpPage;
	ProductDescriptionPage pdpPage;
	CheckoutPage checkoutPage;

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
		plpPage = new ProductListingPage(driver);
		pdpPage = new ProductDescriptionPage(driver);
		checkoutPage = new CheckoutPage(driver);
	}

	@Given("^The Aplication is loaded$")
	public void The_apllication_is_loaded() {
		logger.info("Start of Open URL");
//		driver.get(
//				"https://www.amazon.in/ap/signin?openid.pape.max_auth_age=900&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fhome%3Fpath%3D%252Fgp%252Fyourstore%252Fhome%26useRedirectOnSuccess%3D1%26signIn%3D1%26action%3Dsign-out%26ref_%3Dnav_AccountFlyout_signout&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
		driver.get("https://amazon.in");
		logger.info("End of Open URL");
	}

	@When("^The user login with username and password$")
	public void The_user_enters_the_valid_user_name() throws Throwable {
		logger.info("Start of enter Username");
		loginPage.enterUsername();
		logger.info("End of enter Username");
		Thread.sleep(2000);
		logger.info("Start of click Continue button");
		loginPage.clickContinueButton();
		logger.info("End of click Continue button");
		Thread.sleep(2000);
		logger.info("Start of enter Password");
		loginPage.enterPassword();
		logger.info("End of enter Password");
		Thread.sleep(2000);
		logger.info("Start of click SignIn button");
		loginPage.clickSignInButton();
		logger.info("End of click SignIn button");
	}

//	@Then("^Dashbord will Display$")
//	public void Dashbord_will_display() throws InterruptedException {
//		String HomePage = driver.findElement(By.xpath("//h2[text()='Top picks for you']")).getText();
////		System.out.println("Welcome to home page :" + HomePage);
//	}

	@When("^user search the product$")
	public void user_search_the_product() throws InterruptedException {
//		System.out.println(homePage.getTopPicksText());
		homePage.searchProduct();
		Thread.sleep(4000);
//		homePage.clickSearchFieldSuggestion();
		Thread.sleep(5000);
		String parentWindow = plpPage.getCurrentWindowHandle();
		plpPage.scrollToViewAndClickProduct();
		Thread.sleep(5000);
		Set<String> allWindows = plpPage.getAllWindowHandles();
		for (String eachHandle : allWindows) {
			if (!eachHandle.equals(parentWindow)) {
				driver.switchTo().window(eachHandle);
			}
		}
	}

	@When("^add the product into cart$")
	public void select_a_product_to_purchess() throws InterruptedException {
		pdpPage.clickAddToCartButton();
		pdpPage.clickCheckoutButton();
	}

	@When("^add the delivery address$")
	public void add_address_to_delivery_the_iphone() throws InterruptedException {
		checkoutPage.enterFullname();
		Thread.sleep(3000);
		checkoutPage.enterPhonenumber();
		Thread.sleep(3000);
		checkoutPage.enterPincode();
		Thread.sleep(3000);
		checkoutPage.enterAddress1();
		Thread.sleep(3000);
		checkoutPage.enterAddress2();
		Thread.sleep(3000);
		checkoutPage.enterLandmark();
		Thread.sleep(2000);
		checkoutPage.enterCity();
		Thread.sleep(2000);
		checkoutPage.clickAddAddressButton();
	}

	@When("^testcase is completed$")
	public void teardown() {
		driver.quit();
	}
}
