package tests;

import org.testng.annotations.Test;

import config.Settings;
import pages.LoginPage;
import utils.CustomAssert;

public class LoginTest extends BaseClass {

	@Test
	public void tc2_loginTestMethod() {
		driver.get(Settings.appURL);
		LoginPage lp = new LoginPage(driver);
		lp.performLogin(Settings.userId, Settings.password);
		CustomAssert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/",
				"Submit Performed Successfully");
	}

}
