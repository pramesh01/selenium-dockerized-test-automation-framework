package pageclasses;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserUtilities;


public class OrderSummaryPage extends BrowserUtilities {
	private static final By I_CONFIRM_MY_ORDER_BUTTON_LOCATOR = By.xpath("//*[@id='cart_navigation']/button/span");

	public OrderSummaryPage(WebDriver driver) {
		super(driver);
	}

	public OrderConfirmationPage confirmingOrder() {
		//click(I_CONFIRM_MY_ORDER_BUTTON_LOCATOR);
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

	    try {
	        // Wait until button is clickable
	        WebElement confirmButton = wait.until(
	            ExpectedConditions.elementToBeClickable(I_CONFIRM_MY_ORDER_BUTTON_LOCATOR)
	        );

	        // Try normal click
	        confirmButton.click();
	    } catch (Exception e) {
	        System.out.println("Normal click failed, trying JS click...");

	        // Fallback to JavaScript click
	        WebElement confirmButton = getDriver().findElement(I_CONFIRM_MY_ORDER_BUTTON_LOCATOR);
	        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", confirmButton);
	    }
		return new OrderConfirmationPage(getDriver());
	}

}
