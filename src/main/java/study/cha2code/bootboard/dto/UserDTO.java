package study.cha2code.bootboard.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * User 데이터를 전달하기 위한 DTO
 */
@Data
public class UserDTO {

	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자 ID는 필수 항목입니다.")
	private String username;

	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String password;

	@NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
	private String checkPassword;

	@NotEmpty(message = "이메일은 필수 항목입니다.")
	@Email
	private String email;
}
