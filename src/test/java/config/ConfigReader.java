package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	public static String propertiesLoaded;
	public static String configReaderVariable;

	public static void load() {

		if (propertiesLoaded == null) {
			Properties prop = new Properties();
			InputStream input = null;
			try {
				File file = new File("src/test/resources/config/config.properties");
				// Load the configuration file
				input = new FileInputStream(file);
				// Load properties from input stream
				prop.load(input);
				// Reading properties from config.properties file and assigning them to settings
				// class static fields
				Settings.baseURL = prop.getProperty("BaseURL");
				Settings.hubURL = prop.getProperty("HubURL");
				Settings.isGrid = prop.getProperty("IsGrid");
				Settings.browserName = prop.getProperty("BrowserName");
				Settings.reportPath = prop.getProperty("ReportPath");
				Settings.appURL = prop.getProperty("AppURL");
				Settings.userId = prop.getProperty("UserID");
				Settings.password = prop.getProperty("Password");
				Settings.implicitWait = prop.getProperty("ImplicitWait");
				Settings.pageLoadTimeout = prop.getProperty("PageLoadTimeout");
				Settings.scriptTimeout = prop.getProperty("ScriptTimeout");

				// environmentValue being passed from CLI
				if (System.getProperty("environmentValue") != null) {
					Settings.environmentValue = System.getProperty("environmentValue");
				} else {
					Settings.environmentValue = "LIVE";
				}
				Settings.reportPath = prop.getProperty("ReportPath");

				// ConfigReader static variable
				configReaderVariable = prop.getProperty("configReaderVariable");
				propertiesLoaded = "true";
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				// Close the input stream
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}
