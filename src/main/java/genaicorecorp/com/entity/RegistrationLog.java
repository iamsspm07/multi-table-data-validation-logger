package genaicorecorp.com.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registration_log", schema = "ecom")
public class RegistrationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_log_id")
    private Long registrationLogId;

    @Column(name = "user_name", nullable = false)
    private String userName;
    
    @Column(name = "user_number", unique = true, nullable = false, length = 10)
    private String userNumber;

    @Column(name = "user_mail", nullable = false)
    private String userMail;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;

    @Column(name = "log_date", nullable = false)
    private LocalDateTime logDate;

	public Long getRegistrationLogId() {
		return registrationLogId;
	}

	public void setRegistrationLogId(Long registrationLogId) {
		this.registrationLogId = registrationLogId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public LocalDateTime getLogDate() {
		return logDate;
	}

	public void setLogDate(LocalDateTime logDate) {
		this.logDate = logDate;
	}

	@Override
	public String toString() {
		return "RegistrationLog [registrationLogId=" + registrationLogId + ", userName=" + userName + ", userNumber="
				+ userNumber + ", userMail=" + userMail + ", userRole=" + userRole + ", logDate=" + logDate + "]";
	}

	public RegistrationLog(Long registrationLogId, String userName, String userNumber, String userMail,
			UserRole userRole, LocalDateTime logDate) {
		super();
		this.registrationLogId = registrationLogId;
		this.userName = userName;
		this.userNumber = userNumber;
		this.userMail = userMail;
		this.userRole = userRole;
		this.logDate = logDate;
	}

	public RegistrationLog() {
		super();
	}
}