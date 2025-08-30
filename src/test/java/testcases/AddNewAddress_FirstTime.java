package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageclasses.AddressPage;
import pageclasses.MyAccountPage;
import pojo.FakeAddressPojo;
import testbase.BaseTest;
import utilities.FakeAddressUtility;

//@Listeners(listeners.MyTestListener.class)
public class AddNewAddress_FirstTime extends BaseTest {
	
	private MyAccountPage myAccountPage;
	private FakeAddressPojo fakeAddressPojo;
	private AddressPage addressPage;
	
	@BeforeMethod
	public void initialsetup() {
		myAccountPage=homePage.gotoLoginPage().enterCredentialsValidUser("bloggerdelhi123@gmail.com","Password").submitCredentials();
		fakeAddressPojo=FakeAddressUtility.getFakeAddress();
	}
	
	@Test
	public void enteringFakeAddres() {
		String newAddress=myAccountPage.gotoAddAddressPage().enterFakeAddress(fakeAddressPojo);
		Assert.assertEquals(newAddress, fakeAddressPojo.getAssignNewAddress().toUpperCase());
		
	}

}
