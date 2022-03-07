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
public class CheckoutPage {
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	String product = service.getTestCaseDataColumn(0, "productName");
	Util util;

	public CheckoutPage(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("CheckoutPage.csv");
	}

	public void enterFullname() throws InterruptedException {
		WebElement fullname = util.findElement("fullname");
		fullname.sendKeys(service.getTestCaseDataColumn(0, "fullname"));
	}

	public void enterPhonenumber() throws InterruptedException {
		WebElement phonenumber = util.findElement("phonenumber");
		phonenumber.sendKeys(service.getTestCaseDataColumn(0, "phone"));
	}

	public void enterPincode() throws InterruptedException {
		WebElement pincode = util.findElement("pincode");
		pincode.sendKeys(service.getTestCaseDataColumn(0, "pincode"));
	}

	public void enterAddress1() throws InterruptedException {
		WebElement address1 = util.findElement("address1");
		address1.sendKeys(service.getTestCaseDataColumn(0, "address1"));
	}

	public void enterAddress2() throws InterruptedException {
		WebElement address2 = util.findElement("address2");
		address2.sendKeys(service.getTestCaseDataColumn(0, "address2"));
	}

	public void enterLandmark() throws InterruptedException {
		WebElement landmark = util.findElement("landmark");
		landmark.sendKeys(service.getTestCaseDataColumn(0, "landmark"));
	}

	public void enterCity() throws InterruptedException {
		WebElement city = util.findElement("city");
		city.sendKeys(service.getTestCaseDataColumn(0, "city"));
	}

	public void clickAddAddressButton() throws InterruptedException {
		WebElement addAddressButton = util.findElement("addAddressButton");
		addAddressButton.click();
	}

}
