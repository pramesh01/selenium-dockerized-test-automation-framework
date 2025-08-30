package testcases;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testbase.BaseTest;
import utilities.LoggerUtility;

//@Listeners(listeners.MyTestListener.class)
public class InvalidCred_LoginTest extends BaseTest{
	
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final String INVALID_EMAIL_NAME = "pramesh.cs@hotmail.com";
	private static final String INVALID_PASSWORD = "DEMO12345";

	@Test
	public void Validate_Invalid_Credentials() {
		logger.info("validating for invalid credentials.");
		Assert.assertEquals(
				homePage.gotoLoginPage().
				enterInvalidCredentials(INVALID_EMAIL_NAME,INVALID_PASSWORD).
				getAuthFailMessage(),"Authentication failed.");
		logger.info("successfully verified invalid credentials..");		
	}
}
