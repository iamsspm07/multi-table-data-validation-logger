package genaicorecorp.com.repository;

import genaicorecorp.com.entity.UserProfession;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfessionRepository extends JpaRepository<UserProfession, Long> {
    Optional<UserProfession> findByProfessionName(String professionName);
    
}