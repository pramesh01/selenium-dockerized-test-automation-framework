package pageclasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BrowserUtilities;

public class OrderConfirmationPage extends BrowserUtilities {

	private static final By ORDER_CONFIRMATION_MESSAGE_LOCATOR = By.xpath("//*[@id='center_column']/p[1]");

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
	}

	public String OrderCompleteConfirmation() {
		
		 WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(25));
		 System.out.println("Current URL: " + getDriver().getCurrentUrl());
		 System.out.println(getDriver().getPageSource());
		    WebElement confirmationElement = wait.until(
		        ExpectedConditions.visibilityOfElementLocated(ORDER_CONFIRMATION_MESSAGE_LOCATOR)
		    );
		    return confirmationElement.getText().trim();
	}

}
