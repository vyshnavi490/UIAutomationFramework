package utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementFactory {

	public static void sendText(WebDriver driver, WebElement we, String input) {
		waitForPageLoad(driver);
		we.sendKeys(input);

	}

	public static void performClick(WebDriver driver, WebElement we) {
		waitForPageLoad(driver);
		we.click();
	}

	public static boolean waitForPageLoad(WebDriver driver) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			// wait for jQuery to load
			ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
					} catch (Exception e) {
						// no jQuery present
						return true;
					}
				}
			};

			// wait for Javascript to load
			ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};

			return wait.until(jQueryLoad) && wait.until(jsLoad);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
