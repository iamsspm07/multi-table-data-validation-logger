//package genaicorecorp.com.service;
//
//import genaicorecorp.com.dto.UserDTO;
//import genaicorecorp.com.dto.UserDeleteRequest;
//import genaicorecorp.com.entity.*;
//import genaicorecorp.com.repository.*;
//import jakarta.transaction.Transactional;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class RegistrationService {
//
//    private final UserMasterRepository userMasterRepo;
//    private final RegistrationRepository registrationRepo;
//    private final RegistrationLogRepository registrationLogRepo;
//    private final UserRoleRepository userRoleRepo;
//    private final UserProfessionRepository userProfessionRepo;
//    private final PasswordEncoder passwordEncoder;
//
//    public RegistrationService(UserMasterRepository userMasterRepo,
//                               RegistrationRepository registrationRepo,
//                               RegistrationLogRepository registrationLogRepo,
//                               UserRoleRepository userRoleRepo,
//                               UserProfessionRepository userProfessionRepo,
//                               PasswordEncoder passwordEncoder) {
//        this.userMasterRepo = userMasterRepo;
//        this.registrationRepo = registrationRepo;
//        this.registrationLogRepo = registrationLogRepo;
//        this.userRoleRepo = userRoleRepo;
//        this.userProfessionRepo = userProfessionRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public void registerUser(UserDTO userDTO) {
//        if (userMasterRepo.existsByUserMail(userDTO.getUserMail())) {
//            throw new IllegalArgumentException("Email already exists!");
//        }
//
//        if (userMasterRepo.existsByUserNumber(userDTO.getUserNumber())) {
//            throw new IllegalArgumentException("Phone number already exists!");
//        }
//
//        UserRole role = userRoleRepo.findByRoleName(userDTO.getUserRole())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Role: " + userDTO.getUserRole()));
//
//        UserProfession profession = userProfessionRepo.findByProfessionName(userDTO.getUserProfession())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Profession: " + userDTO.getUserProfession()));
//
//        String encryptedPassword = passwordEncoder.encode(userDTO.getUserPassword());
//
//        Registration registration = new Registration();
//        registration.setUserName(userDTO.getUserName());
//        registration.setUserMail(userDTO.getUserMail());
//        registration.setUserPassword(encryptedPassword);
//        registration.setUserNumber(userDTO.getUserNumber());
//        registration.setUserRole(role);
//        registration.setUserProfession(profession);
//        registration.setCountry(userDTO.getCountry());
//        registration.setCity(userDTO.getCity());
//        registration.setRegistrationDate(LocalDateTime.now());
//        registrationRepo.save(registration);
//
//        UserMaster userMaster = new UserMaster();
//        userMaster.setUserName(userDTO.getUserName());
//        userMaster.setUserMail(userDTO.getUserMail());
//        userMaster.setUserPassword(encryptedPassword);
//        userMaster.setUserNumber(userDTO.getUserNumber());
//        userMaster.setUserRole(role);
//        userMaster.setUserProfession(profession);
//        userMaster.setCountry(userDTO.getCountry());
//        userMaster.setCity(userDTO.getCity());
//        userMaster.setRegistrationDate(LocalDateTime.now());
//        userMasterRepo.save(userMaster);
//
//        RegistrationLog log = new RegistrationLog();
//        log.setUserName(userDTO.getUserName());
//        log.setUserMail(userDTO.getUserMail());
//        log.setUserNumber(userDTO.getUserNumber());
//        log.setUserRole(role);
//        log.setLogDate(LocalDateTime.now());
//        registrationLogRepo.save(log);
//    }
//    
//    
//    
//    // Using this delete method
//    @Transactional
//    public void deleteUser(UserDeleteRequest request) {
//        String userMail = request.getUserMail();
//        String userNumber = request.getUserNumber();
//
//        // Validate that at least one identifier is provided
//        if (userMail == null && userNumber == null) {
//            throw new IllegalArgumentException("Either userMail or userNumber must be provided!");
//        }
//
//        // Check if the user exists based on email or phone number
//        if (userMail != null && !registrationRepo.existsByUserMail(userMail) && !userMasterRepo.existsByUserMail(userMail)) {
//            throw new IllegalArgumentException("User with the provided email does not exist!");
//        }
//        
//        if (userNumber != null && !registrationRepo.existsByUserNumber(userNumber) && !userMasterRepo.existsByUserNumber(userNumber)) {
//            throw new IllegalArgumentException("User with the provided phone number does not exist!");
//        }
//
//        // Delete user by email if provided
//        if (userMail != null) {
//            registrationRepo.deleteByUserMail(userMail);
//            registrationLogRepo.deleteByUserMail(userMail);
//            userMasterRepo.deleteByUserMail(userMail);
//        }
//
//        // Delete user by phone number if provided
//        if (userNumber != null) {
//            registrationRepo.deleteByUserNumber(userNumber);
//            registrationLogRepo.deleteByUserNumber(userNumber);
//            userMasterRepo.deleteByUserNumber(userNumber);
//        }
//    }
//    
//    
//    
//    // Not Used this method
//    @Transactional
//    public void deleteUser1(UserDeleteRequest request) {
//        String userMail = request.getUserMail();
//        String userNumber = request.getUserNumber();
//        
//        if (userMail == null && userNumber == null) {
//            throw new IllegalArgumentException("Either userMail or userNumber must be provided!");
//        }
//
//        // Check if the user exists
//        if (userMail != null) {
//            if (!registrationRepo.existsByUserMail(userMail) && !userMasterRepo.existsByUserMail(userMail)) {
//                throw new IllegalArgumentException("User with the provided email does not exist!");
//            }
//        }
//        
//        if (userNumber != null) {
//            if (!registrationRepo.existsByUserNumber(userNumber) && !userMasterRepo.existsByUserNumber(userNumber)) {
//                throw new IllegalArgumentException("User with the provided phone number does not exist!");
//            }
//        }
//        
//        // Perform deletion in both registration and user master tables based on email or phone number
//        if (userMail != null) {
//            registrationRepo.deleteByEmail(userMail);
//            userMasterRepo.deleteUserByEmail(userMail);
//            registrationLogRepo.deleteLogByEmail(userMail);
//        }
//        
//        if (userNumber != null) {
//            registrationRepo.deleteByPhoneNumber(userNumber);
//            userMasterRepo.deleteUserByPhoneNumber(userNumber);
//            registrationLogRepo.deleteLogByPhoneNumber(userNumber);
//        }
//    }
//
//}


package genaicorecorp.com.service;

import genaicorecorp.com.dto.UserDTO;
import genaicorecorp.com.dto.UserDeleteRequest;
import genaicorecorp.com.entity.*;
import genaicorecorp.com.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    private final UserMasterRepository userMasterRepo;
    private final RegistrationRepository registrationRepo;
    private final RegistrationLogRepository registrationLogRepo;
    private final UserRoleRepository userRoleRepo;
    private final UserProfessionRepository userProfessionRepo;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserMasterRepository userMasterRepo,
                               RegistrationRepository registrationRepo,
                               RegistrationLogRepository registrationLogRepo,
                               UserRoleRepository userRoleRepo,
                               UserProfessionRepository userProfessionRepo,
                               PasswordEncoder passwordEncoder) {
        this.userMasterRepo = userMasterRepo;
        this.registrationRepo = registrationRepo;
        this.registrationLogRepo = registrationLogRepo;
        this.userRoleRepo = userRoleRepo;
        this.userProfessionRepo = userProfessionRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user by saving the user information in appropriate tables.
     * @param userDTO User data transfer object containing user details
     */
    public void registerUser(UserDTO userDTO) {
        logger.info("Registering user: {}", userDTO.getUserMail());

        if (userMasterRepo.existsByUserMail(userDTO.getUserMail()) || registrationRepo.existsByUserMail(userDTO.getUserMail())) {
            throw new IllegalArgumentException("Email already exists!");
        }

        if (userMasterRepo.existsByUserNumber(userDTO.getUserNumber()) || registrationRepo.existsByUserNumber(userDTO.getUserNumber())) {
            throw new IllegalArgumentException("Phone number already exists!");
        }

        UserRole role = userRoleRepo.findByRoleName(userDTO.getUserRole())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Role: " + userDTO.getUserRole()));
        UserProfession profession = userProfessionRepo.findByProfessionName(userDTO.getUserProfession())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Profession: " + userDTO.getUserProfession()));

        String encryptedPassword = passwordEncoder.encode(userDTO.getUserPassword());

        Registration registration = new Registration();
        registration.setUserName(userDTO.getUserName());
        registration.setUserMail(userDTO.getUserMail());
        registration.setUserPassword(encryptedPassword);
        registration.setUserNumber(userDTO.getUserNumber());
        registration.setUserRole(role);
        registration.setUserProfession(profession);
        registration.setCountry(userDTO.getCountry());
        registration.setCity(userDTO.getCity());
        registration.setRegistrationDate(LocalDateTime.now());
        registrationRepo.save(registration);

        UserMaster userMaster = new UserMaster();
        userMaster.setUserName(userDTO.getUserName());
        userMaster.setUserMail(userDTO.getUserMail());
        userMaster.setUserPassword(encryptedPassword);
        userMaster.setUserNumber(userDTO.getUserNumber());
        userMaster.setUserRole(role);
        userMaster.setUserProfession(profession);
        userMaster.setCountry(userDTO.getCountry());
        userMaster.setCity(userDTO.getCity());
        userMaster.setRegistrationDate(LocalDateTime.now());
        userMasterRepo.save(userMaster);

        RegistrationLog log = new RegistrationLog();
        log.setUserName(userDTO.getUserName());
        log.setUserMail(userDTO.getUserMail());
        log.setUserNumber(userDTO.getUserNumber());
        log.setUserRole(role);
        log.setLogDate(LocalDateTime.now());
        registrationLogRepo.save(log);

        logger.info("User registered successfully: {}", userDTO.getUserMail());
    }

    /**
     * Deletes a user based on email or phone number.
     * @param request UserDeleteRequest containing either email or phone number
     */
    @Transactional
    public void deleteUser(UserDeleteRequest request) {
        logger.info("Attempting to delete user: {}", request);

        String userMail = request.getUserMail();
        String userNumber = request.getUserNumber();

        if (userMail == null && userNumber == null) {
            throw new IllegalArgumentException("Either userMail or userNumber must be provided!");
        }

        if (userMail != null && !userMasterRepo.existsByUserMail(userMail) && !registrationRepo.existsByUserMail(userMail)) {
            throw new IllegalArgumentException("User with the provided email does not exist!");
        }
        if (userNumber != null && !userMasterRepo.existsByUserNumber(userNumber) && !registrationRepo.existsByUserNumber(userNumber)) {
            throw new IllegalArgumentException("User with the provided phone number does not exist!");
        }

        if (userMail != null) {
            deleteUserByEmail(userMail);
        }

        if (userNumber != null) {
            deleteUserByPhoneNumber(userNumber);
        }

        logger.info("User deletion successful: {}", request);
    }

    /**
     * Deletes user by email from registration, user master, and registration log.
     * @param userMail User's email
     */
    private void deleteUserByEmail(String userMail) {
        registrationRepo.deleteByUserMail(userMail);
        registrationLogRepo.deleteByUserMail(userMail);
        userMasterRepo.deleteByUserMail(userMail);
    }

    /**
     * Deletes user by phone number from registration, user master, and registration log.
     * @param userNumber User's phone number
     */
    private void deleteUserByPhoneNumber(String userNumber) {
        registrationRepo.deleteByUserNumber(userNumber);
        registrationLogRepo.deleteByUserNumber(userNumber);
        userMasterRepo.deleteByUserNumber(userNumber);
    }
}
