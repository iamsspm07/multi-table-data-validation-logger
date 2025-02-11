package genaicorecorp.com.repository;

import genaicorecorp.com.entity.Registration;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    boolean existsByUserMail(String userMail);

    boolean existsByUserNumber(String userNumber);

    Optional<Registration> findByUserMail(String userMail);

    Optional<Registration> findByUserNumber(String userNumber);

    @Modifying
    @Transactional
    void deleteByUserMail(@Param("userMail") String userMail);

    @Modifying
    @Transactional
    void deleteByUserNumber(@Param("userNumber") String userNumber);
    
    
    @Transactional
    @Query(value = "DELETE FROM ecom.registration WHERE user_mail = :userMail", nativeQuery = true)
    void deleteByEmail(String userMail);

    @Transactional
    @Query(value = "DELETE FROM ecom.registration WHERE user_number = :userNumber", nativeQuery = true)
    void deleteByPhoneNumber(String userNumber);
  
}
