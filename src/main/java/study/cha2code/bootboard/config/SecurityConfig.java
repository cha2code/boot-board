package study.cha2code.bootboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security 사용을 위한 Bean 등록
 */
@Configuration
// 모든 요청 URL을 security로 제어
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// h2-console 경로 csrf 검증 제외
		http.csrf((csrf -> csrf
				.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
		));

		// 경로 접근 권한 설정
		http.authorizeHttpRequests((request -> request
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
		));

		// 로그인, 로그아웃 설정
		http.formLogin((form -> form
				.loginPage("/user/login")
				.defaultSuccessUrl("/")
		));

		http.logout((logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true) // 로그아웃 시 사용자 세션 삭제
		));

		// 콘솔 화면 설정 변경
		http.headers((headers -> headers
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		));

		return http.build();
	}

	// 단방향 암호화 처리 시 사용 되는 passwordEncoder
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	// security 인증을 처리하는 메소드
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
		throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}
}
