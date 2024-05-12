package base;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public class InitializeTest {

	public static WebDriver initializeBrowser(String browserName, String hubURL) throws MalformedURLException {

		DriverManager dm = new DriverManager();
		dm.createWebDriverInstance(browserName, hubURL);
		return dm.getDriver();

	}

}
