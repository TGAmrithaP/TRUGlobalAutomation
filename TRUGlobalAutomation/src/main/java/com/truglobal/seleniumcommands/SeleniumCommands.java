/**
 * 
 */
package com.truglobal.seleniumcommands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.truglobal.util.Util;

/**
 * @author Kumara Swamy
 *
 */
public class SeleniumCommands {
	private static final Logger logger = LogManager.getLogger(SeleniumCommands.class);
	WebDriver driver;
	Util util;

	public SeleniumCommands(WebDriver driver, Util util) {
		this.driver = driver;
		this.util = util;
	}

	/**
	 * Check if the element is visible on the screen The
	 * 
	 * @param element
	 */
	public void checkElementIsVisibleOnPage(WebElement element) {
		try {
			logger.info("Start of Check Element Visibility on Screenof Check Element Visibility on Screen");
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOf(element));
			logger.info("End of Check Element Visibility on Screen");
		} catch (Exception e) {
			logger.error("End of Check Element Visibility on Screen||Error=", e);
		}
	}

	/**
	 * The Click command Implementation
	 * 
	 * Here we'll try with WebElement click first. If it fails then we'll go with
	 * Javascript click
	 * 
	 * @param variableName
	 */
	public void click(String variableName) {
		logger.info("Start of Command Execution||CommandName=Click");
		WebElement element = util.findElement(variableName);

		try {
			element.click();
		} catch (Exception e) {
			try {
				logger.info("WebElement click failed. Applying Autoheal..");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", element);
			} catch (Exception ex) {
				logger.error("Javascript click also failed||Error=", ex);
			}
		}
		logger.info("End of Command Execution||CommandName=Click");
	}

	/**
	 * 
	 * @param variableName
	 * @param str
	 */
	public void sendKeys(String variableName, String str) {
		logger.info("Start of Command Execution||CommandName=SendKeys");
		WebElement element = util.findElement(variableName);

		try {
			element.sendKeys(str);
			logger.info("End of Command Execution||CommandName=SendKeys");
		} catch (Exception e) {
			logger.error("End of Command Execution||CommandName=SendKeys||Error=", e);
		}
	}

	/**
	 * 
	 * @param variableName
	 * @param str
	 */
	public void type(String variableName, String str) {
		logger.info("Start of Command Execution||CommandName=Type");
		WebElement element = util.findElement(variableName);

		try {
			element.clear();
			element.sendKeys(str);
			logger.info("End of Command Execution||CommandName=Type");
		} catch (Exception e) {
			logger.error("End of Command Execution||CommandName=Type||Error=", e);
		}
	}

	public void scrollToViewElement(String variableName) {
		logger.info("Start of Command Execution||CommandName=ScrollToView");
		WebElement element = util.findElement(variableName);
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", element);
			logger.info("End of Command Execution||CommandName=ScrollToView");
		} catch (Exception e) {
			logger.error("End of Command Execution||CommandName=ScrollToView||Error=", e);
		}
	}

	public void waitForAttribute(String variableName, String attributeName, String str) {
		logger.info("Start of Command Execution||CommandName=waitForAttribute");
		WebDriverWait wait = new WebDriverWait(driver, 120);
		while (true) {
			try {
				WebElement element = util.findElement(variableName);
				wait.until(ExpectedConditions.attributeToBe(element, attributeName, str));
				logger.info("End of Command Execution||CommandName=waitForAttribute");
				break;
			} catch (Exception e) {
				logger.error("End of Command Execution||CommandName=waitForAttribute||Error=", e);
			}
		}
	}

	public String getText(String variableName) {
		logger.info("Start of Command Execution||CommandName=GetText");
		WebElement element = util.findElement(variableName);
		String text = null;
		try {
			text = element.getText();
			logger.info("End of Command Execution||CommandName=GetText");
		} catch (Exception e) {
			logger.error("End of Command Execution||CommandName=GetText||Error=", e);
		} finally {
			return text;
		}
	}
}
