package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BrowserUtilities;


public class PaymentPage extends BrowserUtilities {
	
  private static final By PAY_BY_BANKWIRE_LINK_LOCATOR=By.xpath("//*[@title='Pay by bank wire']");
	
  public PaymentPage(WebDriver driver) {
		super(driver);
	}
  
  public OrderSummaryPage proceedToOrderSummary() {
	  click(PAY_BY_BANKWIRE_LINK_LOCATOR);
	  return new OrderSummaryPage(getDriver());
  }
	
	

}
