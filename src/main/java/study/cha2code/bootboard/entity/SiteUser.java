package study.cha2code.bootboard.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 회원 정보 정의를 위한 클래스
 */
@Data
@Entity
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	@Column(unique = true)
	private String email;
}
