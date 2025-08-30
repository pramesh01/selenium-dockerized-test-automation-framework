package pageclasses;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BrowserUtilities;
import utilities.ExtentReportUtility;
import utilities.LoggerUtility;

public class LoginPage extends BrowserUtilities {
	
	private static final By EMAIL_LINK_LOCATOR = By.id("email");
	private static final By PASSWORD_LINK_LOCATOR = By.id("passwd");
	private static final By SIGNIN_SUBMIT_LOCATOR = By.id("SubmitLogin");
	static final By INVALID_AUTHENTICATION_MESSAGE = By.xpath("//*[text()='Authentication failed.']");

	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public LoginPage enterCredentialsValidUser(String email,String password) {
		logger.info("typing email id..through logger");
		type(EMAIL_LINK_LOCATOR, email);
		logger.info("typing password..through logger");
		type(PASSWORD_LINK_LOCATOR,password);
		return new LoginPage(getDriver());
	}

	public MyAccountPage submitCredentials() {
		logger.info("submitting the login credentials..through logger");
		click(SIGNIN_SUBMIT_LOCATOR);
	return new MyAccountPage(getDriver());	
	}
	
	public LoginPage enterInvalidCredentials(String invalidemailid,String password) {
	  logger.info("entering invalid email id..");
	  type(EMAIL_LINK_LOCATOR,invalidemailid);
	  logger.info("entering invalid password...");
	  type(PASSWORD_LINK_LOCATOR,password);
	  click(SIGNIN_SUBMIT_LOCATOR);
	  return new LoginPage(getDriver());
	}
	
	public String getAuthFailMessage() {
		ExtentReportUtility.getTest().info("Fetching the Authentication Message: ");
		return fetchAuthMessage(INVALID_AUTHENTICATION_MESSAGE);
	}
}
