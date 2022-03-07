/**
 * 
 */
package pom;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class ProductListingPage {
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	String product = service.getTestCaseDataColumn(0, "productName");
	Util util;

	public ProductListingPage(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("ProductListingPage.csv");
	}

	public String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}

	public Set<String> getAllWindowHandles() {
		return driver.getWindowHandles();
	}

	public void scrollToViewAndClickProduct() throws InterruptedException {
		String locator = util.getElementIdentifierString("plpProduct", "xpath");
		locator = String.format(locator, product);
		WebElement plpProduct = driver.findElement(By.xpath(locator));
		
		Thread.sleep(5000);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click()", plpProduct);
		try {
			plpProduct.click();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", plpProduct);
		}
	}

}
