package study.cha2code.bootboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.cha2code.bootboard.entity.SiteUser;
import study.cha2code.bootboard.entity.UserRole;
import study.cha2code.bootboard.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserDetailsService를 통해 사용자 객체를 조회하는 클래스
 */
@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

	private final UserRepository repository;

	/**
	 * username으로 사용자를 조회 후 리턴하는 메소드
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<SiteUser> siteUser = repository.findByUsername(username);

		if(siteUser.isEmpty()) {
			// 사용자를 찾을 수 없을 때 exception
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}

		SiteUser user = siteUser.get();
		List<GrantedAuthority> authorities = new ArrayList<>();

		// 권한 부여
		if("ADMIN".equals(username)) {

			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}

		else {

			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}

		// security에서 사용할 User 객체 반환
		return new User(user.getUsername(), user.getPassword(), authorities);
	}
}
