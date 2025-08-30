package pojo;

public class FakeAddressPojo {
	
	private String companyName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalcode;
	private String homePhone;
	private String mobilePhone;
	private String additionalInformation;
	private String assignNewAddress;
	
	public FakeAddressPojo(String companyName,String address1,String address2,String city,String state,
			String postalcode,String homePhone,String mobilePhone,String additionalInformation,
			String assignNewAddress) {
		super();
		    this.companyName = companyName;
	        this.address1 = address1;
	        this.address2 = address2;
	        this.city = city;
	        this.state = state;
	        this.postalcode = postalcode;
	        this.homePhone = homePhone;
	        this.mobilePhone = mobilePhone;
	        this.additionalInformation = additionalInformation;
	        this.assignNewAddress = assignNewAddress;
	   }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAssignNewAddress() {
		return assignNewAddress;
	}

	public void setAssignNewAddress(String assignNewAddress) {
		this.assignNewAddress = assignNewAddress;
	}
	
	@Override
	public String toString() {
		return "FakeAddressPojo [companyName=" + companyName + ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", state=" + state + ", postalcode=" + postalcode + ", homePhone=" + homePhone
				+ ", mobilePhone=" + mobilePhone + ", additionalInformation=" + additionalInformation
				+ ", assignNewAddress=" + assignNewAddress + "]";
	}

}
