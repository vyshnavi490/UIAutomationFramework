package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.ElementFactory;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "username")
	private WebElement userNameElement;

	@FindBy(id = "password")
	private WebElement passwordElement;

	@FindBy(id = "submit")
	private WebElement submit;

	public void performLogin(String UserName, String Password) {
		ElementFactory.sendText(driver, userNameElement, UserName);
		ElementFactory.sendText(driver, passwordElement, Password);
		ElementFactory.performClick(driver, submit);

	}

}
