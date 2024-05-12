package tests;

import java.util.HashMap;

import org.testng.annotations.Test;
import testdata.TestDataProvider;
import utils.CommonUtil;
import utils.CustomAssert;

public class BasicTest extends BaseClass {

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "TestDataProvider")
	public void tc1_validateTitles(String Keys, String Values) {

		HashMap<String, String> testData = CommonUtil.convertToHashMap(Keys, Values);
		driver.get(testData.get("URL"));
		CustomAssert.assertEquals(driver.getTitle(), testData.get("Title"), "Title Validation");
		CustomAssert.assertEquals(driver.getCurrentUrl(), testData.get("URL"), "URL Validation");
	}

}
