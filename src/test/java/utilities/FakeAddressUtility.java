package utilities;

import java.util.Locale;

import com.github.javafaker.Faker;
import pojo.FakeAddressPojo;

public class FakeAddressUtility {
	
	public static FakeAddressPojo getFakeAddress() {
		
		Faker faker=new Faker(Locale.US);
		FakeAddressPojo FakeAddressPojo=new FakeAddressPojo(
				faker.company().name(),faker.address().buildingNumber(),faker.address().cityName(),
				faker.address().city(),faker.address().state(),faker.numerify("#####"),faker.phoneNumber().cellPhone(),
				faker.phoneNumber().cellPhone(), "not required", "My_Alternate_Address");
		
		return FakeAddressPojo;
		
	}

}
