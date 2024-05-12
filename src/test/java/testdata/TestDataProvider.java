package testdata;

import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataProvider {

	@DataProvider(name = "TestDataProvider")
	public Object[][] testData(Method method) {

		String methodName = method.getName();
		String tcId = methodName.split("_")[0]; // Extract tcId from method name

		List<Object[]> testDataList = new ArrayList<>();

		try {
			// Load the Excel file
			FileInputStream file = new FileInputStream("./src/test/resources/testdata/testData.xlsx");

			// Create Workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get the first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Find the row index for the given tcId in the TC_ID column
			int rowIndex = -1;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				String rowTcId = row.getCell(0).getStringCellValue(); // Assuming TC_ID is in the first column (index 0)

				if (tcId.equalsIgnoreCase(rowTcId)) {
					rowIndex = i;
					break;
				}
			}

			// If rowIndex is found, get keys from the matched row and values from the
			// subsequent rows
			if (rowIndex != -1) {
				XSSFRow keysRow = sheet.getRow(rowIndex);
				StringBuilder keysBuilder = new StringBuilder();

				// Extract keys from the matched row
				for (int i = 0; i < keysRow.getLastCellNum(); i++) {
					if (i > 0) {
						keysBuilder.append(";"); // Append semicolon separator
					}
					keysBuilder.append(keysRow.getCell(i).getStringCellValue());
				}

				// Fetch all subsequent rows containing values for the same tcId
				for (int i = rowIndex + 1; i <= sheet.getLastRowNum(); i++) {
					XSSFRow valuesRow = sheet.getRow(i);
					String rowTcId = valuesRow.getCell(0).getStringCellValue();

					if (!tcId.equalsIgnoreCase(rowTcId)) {
						break; // Break if we encounter a row with different tcId
					}

					StringBuilder valuesBuilder = new StringBuilder();

					// Extract values from the row
					for (int j = 0; j < valuesRow.getLastCellNum(); j++) {
						if (j > 0) {
							valuesBuilder.append(";"); // Append semicolon separator
						}
						valuesBuilder.append(valuesRow.getCell(j).getStringCellValue());
					}

					// Add keys and values as single strings to the test data list
					testDataList.add(new Object[] { keysBuilder.toString(), valuesBuilder.toString() });
				}
			}

			// Close the workbook and file
			workbook.close();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Convert the list to a 2D array
		Object[][] testDataArray = new Object[testDataList.size()][];
		testDataList.toArray(testDataArray);

		return testDataArray;
	}

}
