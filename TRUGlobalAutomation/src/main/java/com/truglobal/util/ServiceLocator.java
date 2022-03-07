package com.truglobal.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ServiceLocator {
	private static final Logger logger = LogManager.getLogger(ServiceLocator.class);

	private static ServiceLocator serviceLocator;
	private static List<String[]> testCaseData;
	private static List<String> testCaseDataColumns;

	static {
		try {
			serviceLocator = new ServiceLocator();
		} catch (Exception e) {
			logger.error(String.format("ProcessingStep=End of Service Locator||ERROR=%s", e.getMessage()));
		}
	}

	public static ServiceLocator getInstance() {
		return serviceLocator;
	}

	public static List<String> getTestCaseData(int i) {
		return Arrays.asList(testCaseData.get(i));
	}

	public static List<String[]> getTestCaseData() {
		return testCaseData;
	}

	public static String getTestCaseDataColumn(int rowIndex, int columnIndex) {
		String[] eachRow = testCaseData.get(rowIndex);
		return eachRow[columnIndex];
	}

	public static String getTestCaseDataColumn(int rowIndex, String columnName) {
		String[] eachRow = testCaseData.get(rowIndex);
		int columnIndex = getTestCaseDataColumnIndex(columnName);
		return eachRow[columnIndex];
	}

	public static String getTestCaseDataColumnName(int columnIndex) {
		return testCaseDataColumns.get(columnIndex);
	}

	public static List<String> getTestCaseDataColumnNames() {
		return testCaseDataColumns;
	}

	public static int getTestCaseDataColumnIndex(String columnName) {
		return testCaseDataColumns.indexOf(
				testCaseDataColumns.stream().filter(column -> column.equalsIgnoreCase(columnName)).findFirst().get());
	}

	private ServiceLocator() throws IOException, CsvException {
		String propertiesFile = "TestCaseData.csv";
		try (CSVReader reader = new CSVReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(propertiesFile)))) {

			testCaseData = reader.readAll();
			if (!testCaseData.isEmpty()) {
				testCaseDataColumns = Arrays.asList(testCaseData.remove(0));
			}
		}
	}
}
