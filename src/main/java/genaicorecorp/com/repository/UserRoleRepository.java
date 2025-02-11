package genaicorecorp.com.repository;

import genaicorecorp.com.entity.UserRole;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	Optional<UserRole> findByRoleName(String roleName);
}