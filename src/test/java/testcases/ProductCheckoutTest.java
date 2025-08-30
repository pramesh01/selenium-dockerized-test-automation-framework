package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageclasses.SearchResultPage;
import testbase.BaseTest;
import static com.constants.Size.*;

//@Listeners(listeners.MyTestListener.class)
public class ProductCheckoutTest extends BaseTest {
	private static final String SEARCH_ITEM = "Printed Summer Dress";
	private SearchResultPage searchResultPage;
	
	@BeforeMethod(description = "Searching item for user")
	public void setupforvalidusers() {
		searchResultPage=homePage.gotoLoginPage().enterCredentialsValidUser("bloggerdelhi123@gmail.com", "Password").
				submitCredentials().searchforProduct(SEARCH_ITEM);

	}

	@Test(description = "complete checkout process", groups = { "e2e", "smoke", "sanity" })
	public void checkoutTest() throws InterruptedException {
		String messageAfterCompletion=searchResultPage.clickOntheProductAt(1).selectSize(L)
		.addProductToCart().proceedToCheckout().proceedtoCheckout_from_shopingCartSummaryPage()
		.processAddressCheckout()
		.processToPayment().proceedToOrderSummary().confirmingOrder().OrderCompleteConfirmation();
		
		Assert.assertEquals(messageAfterCompletion,"Your order on My Shop is complete.");
	}
}
