package study.cha2code.bootboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.cha2code.bootboard.entity.SiteUser;

import java.util.Optional;

/**
 * SiteUser 데이터 처리를 위한 interface
 */
public interface UserRepository extends JpaRepository<SiteUser, Long> {

	// SiteUser entity를 조회하는 메소드
	Optional<SiteUser> findByUsername(String username);
}
