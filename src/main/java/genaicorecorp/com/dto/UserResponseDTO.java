package genaicorecorp.com.dto;

public class UserResponseDTO {
    private String message;
    private boolean success;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "UserResponseDTO [message=" + message + ", success=" + success + "]";
	}
	public UserResponseDTO(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	public UserResponseDTO() {
		super();
	}   
}