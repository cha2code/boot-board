package study.cha2code.bootboard.entity;

import lombok.Getter;

/**
 * 사용자 권한 관리를 위한 enum
 */
@Getter
public enum UserRole {

	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

	UserRole(String value) {

		this.value = value;
	}

	private String value;
}
