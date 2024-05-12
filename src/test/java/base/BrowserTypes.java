package base;

public enum BrowserTypes {
	FIREFOX("firefox"), CHROME("chrome"), IE("ie"), SAFARI("safari"), OPERA("opera");

	private final String browserValue;

	BrowserTypes(String browserValue) {
		this.browserValue = browserValue;
	}

	@Override
	public String toString() {
		return browserValue;
	}

}
