/**
 * 
 */
package pom;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.opencsv.exceptions.CsvException;

import util.ServiceLocator;
import util.Util;

/**
 * @author Kumara Swamy
 *
 */
public class HomePage {
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	String product = service.getTestCaseDataColumn(0, "productName");
	Util util;

	public HomePage(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("HomePage.csv");
	}

	public String getTopPicksText() throws InterruptedException {
		WebElement topPicks = util.findElement("topPicks");
		return topPicks.getText();
	}

	public void searchProduct() throws InterruptedException {
		WebElement searchInputField = util.findElement("searchInputField");
		searchInputField.sendKeys(product, Keys.ENTER);
	}

	public void clickSearchFieldSuggestion() throws InterruptedException {
		String locator = util.getElementIdentifierString("searchFieldSuggestion", "xpath");
		locator = String.format(locator, product.toLowerCase());
		WebElement searchFieldSuggestion = driver.findElement(By.xpath(locator));
		Thread.sleep(5000);
		try {
			searchFieldSuggestion.click();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", searchFieldSuggestion);
		}
	}

}
