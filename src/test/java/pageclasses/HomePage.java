package pageclasses;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browsers;
import com.constants.Env;

import utilities.BrowserUtilities;
import utilities.ExtentReportUtility;
import utilities.LoggerUtility;
import utilities.PropertyUtils;

public class HomePage extends BrowserUtilities {
	
	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static final By SIGNIN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");

	public HomePage(Browsers browserName,boolean isHeadless,boolean isGridEnabled) {
		super(browserName,isHeadless, isGridEnabled);
		logger.info("Launching Website..through Logger");
		gotoWebsite(PropertyUtils.propertyFileReader(Env.QA,"url"));
		
	}

	public HomePage(WebDriver driver) {
		super(driver);
		logger.info("Launching Website..through Logger");
		gotoWebsite(PropertyUtils.propertyFileReader(Env.QA,"url"));
		
	}

	public LoginPage gotoLoginPage() {
		logger.info("clicking on Sign in link on HomePage..");
		click(SIGNIN_LINK_LOCATOR);
		return new LoginPage(getDriver());
	}

}
