package genaicorecorp.com.dto;

import java.util.Map;

public class ApiResponse {
    private String message;
    private Map<String, String> errors;

    // Constructor for messages
    public ApiResponse(String message) {
        this.message = message;
    }

    // Constructor for both message and error details (e.g. validation errors)
    public ApiResponse(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
