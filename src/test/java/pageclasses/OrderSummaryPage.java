package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.BrowserUtilities;


public class OrderSummaryPage extends BrowserUtilities {
	private static final By I_CONFIRM_MY_ORDER_BUTTON_LOCATOR = By.xpath("//*[@id='cart_navigation']/button/span");

	public OrderSummaryPage(WebDriver driver) {
		super(driver);
	}

	public OrderConfirmationPage confirmingOrder() {
		click(I_CONFIRM_MY_ORDER_BUTTON_LOCATOR);
		return new OrderConfirmationPage(getDriver());
	}

}
