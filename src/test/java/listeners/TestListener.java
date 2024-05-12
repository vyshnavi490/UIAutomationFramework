package listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import config.Settings;
import reports.ExtentManager;
import tests.BaseClass;

public class TestListener implements ITestListener, WebDriverListener {

	// TestNG listeners && WebDriver listeners
	// You can see the available methods from ITestListener.class and
	// WebDriverListener.class
	// On MAC -> Go to source-> Override/implement Methods-> Select the methods
	// which you want to override and click OK
	ExtentReports extentReport = ExtentManager.createExtentReportInstance();
	ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	// Webdriver Listeners
	@Override
	public void afterClick(WebElement element) {
		test.get().log(Status.PASS, "Performed click on - " + element.getTagName());
	}

	@Override
	public void afterSendKeys(WebElement element, CharSequence... text) {
		test.get().log(Status.PASS, "Sent text -" + text + " to webelement - " + element.getTagName());
	}

	// TestNG listeners
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest localTest = extentReport.createTest(result.getName());
		test.set(localTest);
		Settings.extentVar = test;
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Test Passed - " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Failed", ExtentColor.RED));
		test.get().fail(result.getThrowable());
		TakesScreenshot screenshotInstance = (TakesScreenshot) BaseClass.driver;
		String screenshot = screenshotInstance.getScreenshotAs(OutputType.BASE64);
		test.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Suite execution started");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Suite execution completed");
		extentReport.flush();
	}

}
