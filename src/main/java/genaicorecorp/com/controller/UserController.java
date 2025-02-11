//package genaicorecorp.com.controller;
//
//import genaicorecorp.com.dto.ApiResponse;
//import genaicorecorp.com.dto.UserDTO;
//import genaicorecorp.com.dto.UserDeleteRequest;
//import genaicorecorp.com.service.RegistrationService;
//import jakarta.validation.Valid;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    private final RegistrationService registrationService;
//
//    public UserController(RegistrationService registrationService) {
//        this.registrationService = registrationService;
//    }
//    
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
//        try {
//            registrationService.registerUser(userDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User Registered Successfully!"));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Registration failed: " + e.getMessage()));
//        }
//    }
//    
//    @DeleteMapping("/delete")
//    public ResponseEntity<?> deleteUser(@RequestBody UserDeleteRequest request) {
//        try {
//            registrationService.deleteUser(request);
//            return ResponseEntity.ok(new ApiResponse("User Deleted Successfully!"));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Error: " + e.getMessage()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Deletion failed: " + e.getMessage()));
//        }
//    }
//}

package genaicorecorp.com.controller;

import genaicorecorp.com.dto.ApiResponse;
import genaicorecorp.com.dto.UserDTO;
import genaicorecorp.com.dto.UserDeleteRequest;
import genaicorecorp.com.service.RegistrationService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserController manages user registration and deletion operations.
 * Added enhanced logging for production-level code.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    private final RegistrationService registrationService;

    public UserController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Endpoint for user registration.
     * Handles user registration requests and provides feedback.
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Received request to register user: {}", userDTO.getUserMail());

        try {
            registrationService.registerUser(userDTO);
            logger.info("User successfully registered: {}", userDTO.getUserMail());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User Registered Successfully!"));
        } catch (Exception e) {
            logger.error("Registration failed for user: {}. Error: {}", userDTO.getUserMail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Registration failed: " + e.getMessage()));
        }
    }

    /**
     * Endpoint for user deletion.
     * Handles user deletion requests and provides feedback.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteUser(@RequestBody UserDeleteRequest request) {
        logger.info("Received request to delete user: mail = {}, phone = {}", request.getUserMail(), request.getUserNumber());

        try {
            registrationService.deleteUser(request);
            logger.info("User successfully deleted: mail = {}, phone = {}", request.getUserMail(), request.getUserNumber());
            return ResponseEntity.ok(new ApiResponse("User Deleted Successfully!"));
        } catch (IllegalArgumentException e) {
            logger.error("Error while deleting user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Error: " + e.getMessage()));
        } catch (Exception e) {
            logger.error("Deletion failed for user: mail = {}, phone = {}. Error: {}", request.getUserMail(), request.getUserNumber(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Deletion failed: " + e.getMessage()));
        }
    }
}
