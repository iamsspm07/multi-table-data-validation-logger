package genaicorecorp.com.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_master", schema = "ecom")
public class UserMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_mail", unique = true, nullable = false)
    private String userMail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_number", unique = true, nullable = false, length = 10)
    private String userNumber;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "user_profession_id", nullable = false)
    private UserProfession userProfession;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "deregister_date")
    private LocalDateTime deregisterDate; // Nullable for active users

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserProfession getUserProfession() {
		return userProfession;
	}

	public void setUserProfession(UserProfession userProfession) {
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

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDateTime getDeregisterDate() {
		return deregisterDate;
	}

	public void setDeregisterDate(LocalDateTime deregisterDate) {
		this.deregisterDate = deregisterDate;
	}

	@Override
	public String toString() {
		return "UserMaster [userId=" + userId + ", userName=" + userName + ", userMail=" + userMail + ", userPassword="
				+ userPassword + ", userNumber=" + userNumber + ", userRole=" + userRole + ", userProfession="
				+ userProfession + ", country=" + country + ", city=" + city + ", registrationDate=" + registrationDate
				+ ", deregisterDate=" + deregisterDate + "]";
	}

	public UserMaster(Long userId, String userName, String userMail, String userPassword, String userNumber,
			UserRole userRole, UserProfession userProfession, String country, String city,
			LocalDateTime registrationDate, LocalDateTime deregisterDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.userPassword = userPassword;
		this.userNumber = userNumber;
		this.userRole = userRole;
		this.userProfession = userProfession;
		this.country = country;
		this.city = city;
		this.registrationDate = registrationDate;
		this.deregisterDate = deregisterDate;
	}

	public UserMaster() {
		super();
	}  
}