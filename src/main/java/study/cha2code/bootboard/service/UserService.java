package study.cha2code.bootboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import study.cha2code.bootboard.entity.SiteUser;
import study.cha2code.bootboard.exception.DataNotFoundException;
import study.cha2code.bootboard.repository.UserRepository;

import java.util.Optional;

/**
 * user 정보 로직을 처리하는 service
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final BCryptPasswordEncoder passwordEncoder;

	// user를 생성하는 메소드
	public SiteUser create(String username, String email, String password) {

		SiteUser user = new SiteUser();

		user.setUsername(username);
		user.setEmail(email);
		// 비밀번호 암호화 후 저장
		user.setPassword(passwordEncoder.encode(password));
		this.repository.save(user);

		return user;
	}

	// user 객체를 검색하는 메소드
	public SiteUser getUser(String username) {

		Optional<SiteUser> siteUser = repository.findByUsername(username);

		if(siteUser.isPresent()) {

			return siteUser.get();
		}

		else {

			throw new DataNotFoundException("사용자를 찾을 수 없습니다.");
		}
	}
}
