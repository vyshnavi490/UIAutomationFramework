package tests;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import base.InitializeTest;
import config.ConfigReader;
import config.Settings;
import listeners.TestListener;
import mainclasses.GridStatusChecker;
import utils.ElementFactory;

//@Listeners(TestListener.class)
public class BaseClass {

	// Write data provider classes
	public static WebDriver driver;
	SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	public void setup() throws MalformedURLException {
		ConfigReader.load();
		String hubURL = "";
		if (Settings.isGrid.equals("true")) {
			hubURL = Settings.hubURL;
			boolean isGridRunning = GridStatusChecker.checkGridStatus();
			if (!isGridRunning) {
				System.exit(0);
			}
		}
		driver = InitializeTest.initializeBrowser(Settings.browserName, hubURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(Settings.implicitWait)));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(Integer.parseInt(Settings.scriptTimeout)));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(Settings.pageLoadTimeout)));
		driver.manage().window().maximize();
		ElementFactory.waitForPageLoad(driver);
	}

	@AfterMethod
	public void tearDown() {

		if (driver != null) {
			ElementFactory.waitForPageLoad(driver);
			driver.quit();
		}

	}

}
