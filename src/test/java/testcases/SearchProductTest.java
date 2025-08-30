package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageclasses.MyAccountPage;
import testbase.BaseTest;
import utilities.ExtentReportUtility;

//@Listeners(listeners.MyTestListener.class)
public class SearchProductTest extends BaseTest {

  private MyAccountPage myaccountPage;
  private static final String SEARCH_ITEMS="Printed Summer Dress";
	
  @BeforeMethod(description = "for valid credentials")
	public void setupforvalidusers() {
	  
		myaccountPage = homePage.gotoLoginPage().
				enterCredentialsValidUser("bloggerdelhi123@gmail.com", "Password").submitCredentials();	
	}

	@Test(description = "verifying product search", groups = { "e2e", "sanity", "smoke" })
	public void verifyproductsearchTest() {
		
		boolean actualSearchResult=myaccountPage.searchforProduct(SEARCH_ITEMS).isProductPresentinSearchList(SEARCH_ITEMS);
		Assert.assertEquals(actualSearchResult, true);
		
	}
}
