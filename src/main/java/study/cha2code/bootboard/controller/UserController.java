package study.cha2code.bootboard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.cha2code.bootboard.dto.UserDTO;
import study.cha2code.bootboard.service.UserService;

/**
 * user의 회원 가입과 로그인을 처리하는 controller
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService service;

	@GetMapping("/login")
	public String login() {

		return "loginForm";
	}

	@GetMapping("/signup")
	public String signup(UserDTO userDTO) {

		return "signupForm";
	}

	@PostMapping("/signup")
	public String signup(@Valid UserDTO userDTO, BindingResult bindingResult) {

		// Validation 에러 발생 시 실행
		if(bindingResult.hasErrors()) {

			return "signupForm";
		}

		// 입력 받은 비밀번호와 비밀번호 확인이 같지 않을 때 실행
		if(!userDTO.getPassword().equals(userDTO.getCheckPassword())) {
			// (필드명, 오류 코드, 오류 메세지)
			bindingResult.rejectValue("checkPassword", "passwordInCorrect", "패스워드가 일치하지 않습니다.");

			return "signupForm";
		}

		// 가입 시 이미 존재하는 사용자가 있는지 검사 및 처리
		try{

			service.create(userDTO.getUsername(), userDTO.getEmail(),userDTO.getPassword());

		}catch (DataIntegrityViolationException e) {

			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");

			return "signupForm";
		}

		return "redirect:/";
	}
}
