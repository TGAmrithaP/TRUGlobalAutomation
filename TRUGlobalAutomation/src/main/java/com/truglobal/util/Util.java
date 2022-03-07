/**
 * 
 */
package com.truglobal.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

/**
 * @author Kumara Swamy
 *
 */
public class Util {
	private static final Logger logger = LogManager.getLogger(Util.class);
	public List<String[]> csvAllRows;
	public List<String> headers;
	WebDriver driver;

	public Util(WebDriver driver) {
		this.driver = driver;
	}

	public void readCSV(String filename) throws IOException, CsvException {
		try (CSVReader reader = new CSVReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filename)))) {

			csvAllRows = reader.readAll();
			headers = Arrays.asList(csvAllRows.get(0));
			headers = headers.stream().map(String::toLowerCase).map(String::trim).collect(Collectors.toList());
			logger.info(String.format("CSV Headers %s", headers));
		}
	}

	public WebElement findElement(String variableName) throws InterruptedException {
		System.out.println("");
		String[] row = csvAllRows.stream().filter(eachRow -> eachRow[0].equalsIgnoreCase(variableName)).findFirst()
				.get();
		logger.info(String.format("Filtered row from the CSV file %s", Arrays.asList(row)));
		if (row != null) {
			while (true) {
				Date startTime = new Date();
				long maxTime = startTime.getTime() / 1000 + 120 * 1000;
				for (int i = 1; i < row.length; i++) {
					try {
						By locator = getElementIdentifier(row, headers.get(i));
						WebElement element = driver.findElement(locator);
						WebDriverWait wait = new WebDriverWait(driver, 120);
						wait.until(ExpectedConditions.visibilityOf(element));
						if (element.isDisplayed()) {
							logger.info("The element is visible on the screen");
							return element;
						}
						Thread.sleep(1000);
					} catch (Exception e) {
//						System.out.println(row[0] + " failed with " + headers.get(i));
						logger.info(String.format("%s failed with %s", row[0], headers.get(i)));
						Thread.sleep(1000);
						Date endTime = new Date();
						long responseTime = endTime.getTime() / 1000;
						if (responseTime >= maxTime) {
							return null;
						}
					}
				}
			}
		} else {
			return null;
		}
	}

	public By getElementIdentifier(String[] row, String locatorType) {
//		System.out.println(row[0] + " trying with " + locatorType);
		logger.info(String.format("%s trying with %s", row[0], locatorType));
		if (row.length > 1) {
			locatorType = locatorType.toLowerCase().trim();
			int index = 0;
			index = headers.indexOf(locatorType);

			if (locatorType.equalsIgnoreCase("default")) {
				locatorType = row[index];
				if (locatorType != null && !locatorType.equalsIgnoreCase("")) {
					index = headers.indexOf(locatorType);
//					System.out.println(row[0] + " default is " + locatorType);
					logger.info(String.format("%s default is %s", row[0], locatorType));
				} else {
					return null;
				}
			}

			if (row[index] == null || row[index].equalsIgnoreCase("")) {
				return null;
			}

			switch (locatorType) {
			case "id":
				return By.id(row[index]);
			case "css":
				return By.cssSelector(row[index]);
			case "name":
				return By.name(row[index]);
			case "linktext":
				return By.xpath(row[index]);
			case "partiallinktext":
				return By.xpath(row[index]);
			case "classname":
				return By.xpath(row[index]);
			default:
				index = headers.indexOf("xpath");
				return By.xpath(row[index]);
			}
		} else {
			return null;
		}
	}

	public String getElementIdentifierString(String variableName, String locatorType) {
		Optional<String[]> row = csvAllRows.stream().filter(eachRow -> eachRow[0].equalsIgnoreCase(variableName))
				.findFirst();
		if (row.isPresent()) {
//			variableName,id,css,xpath,name,linkText,partialLinkText,className
			locatorType = locatorType.toLowerCase().trim();
			int index = 0;
			index = headers.indexOf(locatorType);
			return row.get()[index];
		} else {
			return null;
		}
	}
}
