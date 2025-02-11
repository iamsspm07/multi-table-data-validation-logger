package genaicorecorp.com.dto;

public class UserDeleteRequest {
    private String userMail;
    private String userNumber;
    
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	@Override
	public String toString() {
		return "UserDeleteRequest [userMail=" + userMail + ", userNumber=" + userNumber + "]";
	}
	public UserDeleteRequest(String userMail, String userNumber) {
		super();
		this.userMail = userMail;
		this.userNumber = userNumber;
	}
	public UserDeleteRequest() {
		super();
	}
}
