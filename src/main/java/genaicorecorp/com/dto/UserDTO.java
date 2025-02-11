package genaicorecorp.com.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {
    
    @NotNull(message = "Username cannot be null")
    private String userName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String userMail;

    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$", message = "Password must be 8-10 characters long and contain at least one letter, one number, and one special character")
    private String userPassword;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String userNumber;

    @NotNull(message = "Role cannot be null")
    private String userRole;

    @NotNull(message = "Profession cannot be null")
    private String userProfession;

    @NotNull(message = "Country cannot be null")
    private String country;

    @NotNull(message = "City cannot be null")
    private String city;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserProfession() {
		return userProfession;
	}

	public void setUserProfession(String userProfession) {
		this.userProfession = userProfession;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", userMail=" + userMail + ", userPassword=" + userPassword
				+ ", userNumber=" + userNumber + ", userRole=" + userRole + ", userProfession=" + userProfession
				+ ", country=" + country + ", city=" + city + "]";
	}

	public UserDTO(@NotNull(message = "Username cannot be null") String userName,
			@NotNull(message = "Email cannot be null") @Email(message = "Invalid email format") String userMail,
			@NotNull(message = "Password cannot be null") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$", message = "Password must be 8-10 characters long and contain at least one letter, one number, and one special character") String userPassword,
			@NotNull(message = "Phone number cannot be null") @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits") String userNumber,
			@NotNull(message = "Role cannot be null") String userRole,
			@NotNull(message = "Profession cannot be null") String userProfession,
			@NotNull(message = "Country cannot be null") String country,
			@NotNull(message = "City cannot be null") String city) {
		super();
		this.userName = userName;
		this.userMail = userMail;
		this.userPassword = userPassword;
		this.userNumber = userNumber;
		this.userRole = userRole;
		this.userProfession = userProfession;
		this.country = country;
		this.city = city;
	}

	public UserDTO() {
		super();
	}
}