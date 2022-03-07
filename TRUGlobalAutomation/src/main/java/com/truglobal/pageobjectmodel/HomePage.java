/**
 * 
 */
package com.truglobal.pageobjectmodel;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.opencsv.exceptions.CsvException;

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

	public HomePage(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("HomePage.csv");
	}

	public void clickonHomePage() throws InterruptedException {
		WebElement homePage = util.findElement("homePage");
		try {
			homePage.click();
		} catch (Exception e) {
			Thread.sleep(5000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", homePage);
		}
	}

	public void clickonAppLauncher() throws InterruptedException {
		WebElement applauncher = util.findElement("appLauncher");
		applauncher.click();
	}

	public void clickonProject() throws InterruptedException {
		WebElement searapp = util.findElement("searchApp");
		searapp.sendKeys("sal");

		WebElement projectna = util.findElement("projectName");
		Actions act = new Actions(driver);
		act.moveToElement(projectna).click().perform();
	}

	public boolean verifyPagetitle() throws InterruptedException {
		WebElement pagetitle = util.findElement("projectTitle");
		return pagetitle.isDisplayed();

	}

	public void recentContact() throws InterruptedException {
		WebElement recentcon = util.findElement("Recent contacts");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", recentcon);

	}

	public void verifyrecconli() throws InterruptedException {
		WebElement recentcon = util.findElement("Recent contacts");
		Thread.sleep(5000);
		String ulteredtext = recentcon.getText();
		System.out.println("the text is : " + ulteredtext);
		String strdemo = ulteredtext.substring(17, 18);
		System.out.println("the text is : " + strdemo);
		int newli = Integer.parseInt(strdemo);
		List<WebElement> listel = driver.findElements(
				By.xpath("//span[contains(text(),'Recent Contacts')]/following::div[@class='flex-icon']"));
		int count = listel.size();
		System.out.println("the count is : " + count);
		Assert.assertEquals(count, newli);

	}

	public void clickOnSalesforceAccount() throws InterruptedException {
		WebElement element = util.findElement("SalesForceAccounts");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void verifyAccount() throws InterruptedException {
		WebElement verifyAcc = util.findElement("AccountVerify");
		String act = verifyAcc.getText();
		String exp = "Acme (Sample)";
		if (act.equals(exp)) {
			logger.info("Acme is Validated");
		} else {
			logger.info("Acme is not Validated");
		}

	}

	public void verifyGlobal() throws InterruptedException {
		WebElement verifyGlobalMedia = util.findElement("Global");
		String act1 = verifyGlobalMedia.getText();
		String exp1 = "Global Media (Sample)";
		if (act1.equals(exp1)) {
			logger.info("Global Media is Validated");
		} else {
			logger.info("Global Media is not Validated");
		}

	}

}
