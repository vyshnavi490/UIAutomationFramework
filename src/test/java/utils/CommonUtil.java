package utils;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CommonUtil {

	public static HashMap<String, String> convertToHashMap(String keysString, String valuesString) {

		String[] keys = keysString.split(";");
		String[] values = valuesString.split(";");

		// Ensure that the keys and values arrays have the same length
		if (keys.length != values.length) {
			throw new IllegalArgumentException("Keys and values arrays have different lengths");
		}

		// Create a HashMap to store the key-value pairs
		HashMap<String, String> keyValueMap = new LinkedHashMap<>();

		// Populate the HashMap with keys and values
		for (int i = 0; i < keys.length; i++) {
			keyValueMap.put(keys[i], values[i]);
		}
		return keyValueMap;

	}

}
