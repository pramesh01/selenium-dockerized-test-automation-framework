package pageclasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BrowserUtilities;



public class ShipingPage extends BrowserUtilities{
     private static final By TERMS_OF_SERVICE_CHECKBOX_LOCATOR=By.id("cgv");
    		 private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR=By.xpath("//button[@name='processCarrier']");
	
	public ShipingPage(WebDriver driver) {
		super(driver);
		
	}
	
	public PaymentPage processToPayment() {
		 System.out.println("Current URL: " + getDriver().getCurrentUrl());

		    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

		    WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cgv")));
		    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", checkbox);

		    // Force JS click
		    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", checkbox);
		    System.out.println("Checkbox clicked via JS ✅");

		    WebElement proceedBtn = wait.until(ExpectedConditions.presenceOfElementLocated(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR));
		    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", proceedBtn);

		    // Force JS click on proceed
		    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", proceedBtn);
		    System.out.println("Proceed button clicked via JS ✅");

		    return new PaymentPage(getDriver());
	}

}
