package testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pojo.User;
import testbase.BaseTest;

//@Listeners(listeners.MyTestListener.class)
public class LoginTest extends BaseTest {

	@Test(dataProviderClass = dataproviders.TestDataProviders.class, dataProvider ="xlsx_loginTestData",retryAnalyzer=listeners.MyRetryAnalyzer.class)
	public void loginTest_xlsx(User user) {

		Assert.assertEquals(
				homePage.gotoLoginPage().
				enterCredentialsValidUser(user.getEmailID(),user.getPassword()).
				submitCredentials().
				getLoggedinuserText(),
				"Parthik Verma");
	}
	
	
	@Test(dataProviderClass = dataproviders.TestDataProviders.class, dataProvider ="json_loginTestData",retryAnalyzer=listeners.MyRetryAnalyzer.class)
	public void loginTest_json(User user) {
		Assert.assertEquals(
				homePage.gotoLoginPage().
				enterCredentialsValidUser(user.getEmailID(),user.getPassword()).
				submitCredentials().
				getLoggedinuserText(),
				"Parthik Verma");
	}
	
	@Test(dataProviderClass = dataproviders.TestDataProviders.class, dataProvider ="csv_loginTestData",retryAnalyzer=listeners.MyRetryAnalyzer.class)
	public void loginTest_csv(User user) {
		Assert.assertEquals(
				homePage.gotoLoginPage().
				enterCredentialsValidUser(user.getEmailID(),user.getPassword()).
				submitCredentials().
				getLoggedinuserText(),
				"Parthik Verma");
	}
}