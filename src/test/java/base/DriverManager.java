package base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public void createWebDriverInstance(String browserName, String hubURL) throws MalformedURLException {
		
			if (browserName.equalsIgnoreCase("chrome")) {
				webDriver.set(new ChromeDriver());
			} else if (browserName.equalsIgnoreCase("firefox")) {
				webDriver.set(new FirefoxDriver());
			}
	}

//	public void setDriver(RemoteWebDriver rwd) {
//		webDriver.set(rwd);
//	}

	public WebDriver getDriver() {
		return webDriver.get();
	}

}
