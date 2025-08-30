package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BrowserUtilities;

public class ShoppingCartSummaryPage extends BrowserUtilities {
	
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR=By.xpath("(//a[@title='Proceed to checkout'])[2]");
	
	public ShoppingCartSummaryPage(WebDriver driver) {
		super(driver);
	}
	
	public BillingAddressPage proceedtoCheckout_from_shopingCartSummaryPage() {
		click(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		return new BillingAddressPage(getDriver());
	}
}
