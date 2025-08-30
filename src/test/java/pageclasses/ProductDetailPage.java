package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.constants.Size;
import utilities.BrowserUtilities;

public class ProductDetailPage extends BrowserUtilities {

	private static final By DROP_DOWN_SIZE_LOCATOR = By.id("group_1");
	private static final By ADD_TO_CART_BUTTON_LOCATOR = By.name("Submit");
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.xpath("//a[@title='Proceed to checkout']");

	public ProductDetailPage(WebDriver driver) {
		super(driver);
	}

	public ProductDetailPage selectSize(Size size) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectFromDropDown(DROP_DOWN_SIZE_LOCATOR, size.toString());
		return new ProductDetailPage(getDriver());
	}

	public ProductDetailPage addProductToCart() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		click(ADD_TO_CART_BUTTON_LOCATOR);
		return new ProductDetailPage(getDriver());
	 }

	public ShoppingCartSummaryPage proceedToCheckout() {
		click(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		return new ShoppingCartSummaryPage(getDriver());
	}
}
