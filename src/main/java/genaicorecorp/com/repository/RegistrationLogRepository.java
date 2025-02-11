package genaicorecorp.com.repository;

import genaicorecorp.com.entity.RegistrationLog;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationLogRepository extends JpaRepository<RegistrationLog, Long> {

	Optional<RegistrationLog> findByUserMail(String userMail);

	Optional<RegistrationLog> findByUserNumber(String userNumber);

	@Modifying
	@Transactional
	void deleteByUserMail(@Param("userMail") String userMail);

	@Modifying
	@Transactional
	void deleteByUserNumber(@Param("userNumber") String userNumber);

	@Transactional
	@Query(value = "DELETE FROM ecom.registration_log WHERE user_mail = :userMail", nativeQuery = true)
	void deleteLogByEmail(String userMail);

	@Transactional
	@Query(value = "DELETE FROM ecom.registration_log WHERE user_number = :userNumber", nativeQuery = true)
	void deleteLogByPhoneNumber(String userNumber);

}
