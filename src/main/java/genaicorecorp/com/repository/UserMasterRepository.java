package genaicorecorp.com.repository;

import genaicorecorp.com.entity.UserMaster;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {

    boolean existsByUserMail(String userMail);

    boolean existsByUserNumber(String userNumber);

    Optional<UserMaster> findByUserMail(String userMail);

    Optional<UserMaster> findByUserNumber(String userNumber);
    
    @Modifying
    @Transactional
    void deleteByUserMail(@Param("userMail") String userMail);

    @Modifying
    @Transactional
    void deleteByUserNumber(@Param("userNumber") String userNumber);
    

    @Transactional
    @Query(value = "DELETE FROM ecom.user_master WHERE user_mail = :userMail", nativeQuery = true)
    void deleteUserByEmail(String userMail);

    @Transactional
    @Query(value = "DELETE FROM ecom.user_master WHERE user_number = :userNumber", nativeQuery = true)
    void deleteUserByPhoneNumber(String userNumber);
 
}
