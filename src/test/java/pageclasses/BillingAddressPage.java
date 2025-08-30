package pageclasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BrowserUtilities;

public class BillingAddressPage extends BrowserUtilities {
       private static final By CHECKOUT_BUTTON_LOCATOR=By.xpath("//button[@name='processAddress']");
	
	public BillingAddressPage(WebDriver driver) {
		super(driver);
		
	}
	/*public ShipingPage processAddressCheckout() {
		click(CHECKOUT_BUTTON_LOCATOR);
		return new ShipingPage(getDriver());
	}*/
	
	public ShipingPage processAddressCheckout() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

        // Wait for button to be clickable
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT_BUTTON_LOCATOR));
        checkoutBtn.click();

        // Wait until we are on Shipping page (step=4) → Terms of service checkbox is present
        //wait.until(ExpectedConditions.urlContains("step=4"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cgv")));  

        return new ShipingPage(getDriver());
    }

}
