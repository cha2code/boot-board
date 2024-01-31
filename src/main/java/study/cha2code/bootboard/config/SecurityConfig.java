package study.cha2code.bootboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// h2-console 경로 csrf 검증 제외
		http.csrf((csrf -> csrf
				.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
		);

		// 경로 접근 권한 설정
		http.authorizeHttpRequests((request -> request
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
		);

		// 콘솔 화면 설정 변경
		http.headers((headers -> headers
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
		);

		return http.build();
	}
}
