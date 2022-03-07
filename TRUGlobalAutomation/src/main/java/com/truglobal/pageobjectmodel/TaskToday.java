
package com.truglobal.pageobjectmodel;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.opencsv.exceptions.CsvException;

import com.truglobal.util.ServiceLocator;
import com.truglobal.util.Util;

public class TaskToday {
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	Util util;

	public TaskToday(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("Task Bar.csv");
	}

	public void clickOnTodaysTask() throws InterruptedException {
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,600)");
		Thread.sleep(3000);
		WebElement task = util.findElement("viewAll");
		task.click();
	}

}
