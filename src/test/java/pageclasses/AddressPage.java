package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pojo.FakeAddressPojo;
import utilities.BrowserUtilities;

public class AddressPage extends BrowserUtilities {

	private static final By COMPANY_TEXT_LOCATOR = By.id("company");
	private static final By ADDRESS1_TEXT_LOCATOR = By.id("address1");
	private static final By ADDRESS2_TEXT_LOCATOR = By.id("address2");
	private static final By CITY_TEXT_LOCATOR = By.id("city");
	private static final By STATE_TEXT_LOCATOR = By.id("id_state");
	private static final By POSTCODE_TEXT_LOCATOR = By.id("postcode");
	private static final By HOME_PHONE_TEXT_LOCATOR = By.id("phone");
	private static final By MOBILE_PHONE_TEXT_LOCATOR = By.id("phone_mobile");
	private static final By ADDITIONAL_INFORMATION_TEXT_LOCATOR = By.id("other");
	private static final By ASSIGN_ANOTHER_ADDRESS_TEXT_LOCATOR = By.id("alias");
	private static final By SAVE_BUTTON_LOCATOR = By.id("submitAddress");
	private static final By MY_ADDRESS_CONFIRMATION_LOCATOR = By.tagName("h3");
	
	public AddressPage(WebDriver driver) {
		super(driver);	
	}
	 
	public String enterFakeAddress(FakeAddressPojo address) {
		
		type(COMPANY_TEXT_LOCATOR, address.getCompanyName());
		type(ADDRESS1_TEXT_LOCATOR, address.getAddress1());
		type(ADDRESS2_TEXT_LOCATOR, address.getAddress2());
		type(CITY_TEXT_LOCATOR, address.getCity());
		type(STATE_TEXT_LOCATOR, address.getState());
		type(POSTCODE_TEXT_LOCATOR, address.getPostalcode());
		type(HOME_PHONE_TEXT_LOCATOR, address.getHomePhone());
		type(MOBILE_PHONE_TEXT_LOCATOR, address.getMobilePhone());
		type(ADDITIONAL_INFORMATION_TEXT_LOCATOR, address.getAdditionalInformation());
		clearText(ASSIGN_ANOTHER_ADDRESS_TEXT_LOCATOR);
		type(ASSIGN_ANOTHER_ADDRESS_TEXT_LOCATOR, address.getAssignNewAddress());
		click(SAVE_BUTTON_LOCATOR);
		String newAddress = fetchAuthMessage(MY_ADDRESS_CONFIRMATION_LOCATOR);
		return newAddress;
	}	

}
