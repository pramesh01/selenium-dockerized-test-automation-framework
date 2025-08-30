package dataproviders;

import java.util.Iterator;
import org.testng.annotations.DataProvider;
import pojo.User;
import utilities.CSVReaderUtility;
import utilities.JSONReaderUtility;
import utilities.XLSReaderUtility;

public class TestDataProviders {
	
	@DataProvider(name="xlsx_loginTestData")
	public Iterator<User> loginXLSTestDataProvider() {
		return XLSReaderUtility.fetchXLSData("LoginTestData.xlsx");
	}
	
	@DataProvider(name="json_loginTestData")
	public Iterator<Object[]> loginJSONTestDataProvider() {
		return JSONReaderUtility.fetchJSONData("LoginTestData.json");
	}
	
	@DataProvider(name="csv_loginTestData")
	public Iterator<User> loginCVSTestDataProvider() {
		return CSVReaderUtility.fetchCSVData("LoginTestData.csv");
	}


}
