package config;

import com.aventstack.extentreports.ExtentTest;

public class Settings {
	public static String dbUrl;
	public static String dbUsername;
	public static String dbPassword;
	public static String environmentValue;
	public static String baseURL;
	public static String hubURL;
	public static String isGrid;
	public static String browserName;
	public static String reportPath;
	public static String userId;
	public static String password;
	public static String appURL;
	public static ThreadLocal<ExtentTest> extentVar;
	public static String pageLoadTimeout;
	public static String scriptTimeout;
	public static String implicitWait;
}
