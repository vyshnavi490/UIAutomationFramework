package utils;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

import config.Settings;

public class CustomAssert {

	public static void assertEquals(Object actual, Object expected, String message) {

		Assert.assertEquals(actual, expected, message);
		Settings.extentVar.get().log(Status.PASS, "Test Step - validation Successful - Message - " + message);

	}
}
