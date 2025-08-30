package pojo;

public class User {
	
	private String EmailID;
	private String Password;
	
	public User(String EmailID,String Password) {
		super();
		this.EmailID=EmailID;
		this.Password=Password;
	}

	public String getEmailID() {
		return EmailID;
	}

	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	@Override
	public String toString() {
		return "User [EmailID=" + EmailID + ", Password=" + Password + "]";
	}

}
