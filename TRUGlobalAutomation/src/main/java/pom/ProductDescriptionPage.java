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
public class ProductDescriptionPage {
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	String product = service.getTestCaseDataColumn(0, "productName");
	Util util;

	public ProductDescriptionPage(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("ProductDescriptionPage.csv");
	}

	public void clickAddToCartButton() throws InterruptedException {
		WebElement addToCartButton = util.findElement("addToCartButton");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", addToCartButton);
		Thread.sleep(5000);

		addToCartButton.click();
	}

	public void clickCheckoutButton() throws InterruptedException {
		WebElement checkoutButton = util.findElement("checkoutButton");
		checkoutButton.click();
	}

}
