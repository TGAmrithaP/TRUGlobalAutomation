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

public class QuaterlyPerformance {
	WebDriver driver;
	ServiceLocator service = ServiceLocator.getInstance();
	Util util;

	public QuaterlyPerformance(WebDriver driver) throws IOException, CsvException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new Util(driver);
		util.readCSV("Quarterly PerformancePage.csv");
	}

	public void getClickOnQuaterlyPerformance() throws InterruptedException {
		WebElement quaterlyPerformance = util.findElement("quarterlyPerformance");
		quaterlyPerformance.click();
	}

}
