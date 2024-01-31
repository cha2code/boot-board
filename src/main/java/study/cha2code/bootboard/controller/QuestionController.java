package study.cha2code.bootboard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.cha2code.bootboard.dto.AnswerDTO;
import study.cha2code.bootboard.dto.QuestionDTO;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.entity.SiteUser;
import study.cha2code.bootboard.service.QuestionService;
import study.cha2code.bootboard.service.UserService;

import java.security.Principal;

/**
 * 질문 게시판 mapping을 위한 controller
 */
@Slf4j
@Controller
// final이 붙은 속성을 생성자로 객체 주입 (= QuestionService)
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

	// 생성자 주입
	private final QuestionService service;
	private final UserService userService;

	// 질문 게시판 페이지
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

		// service에서 조회 한 페이지 가져오기 (한 페이지에 글 10개)
		Page<Question> paging = this.service.getList(page);
		// questionList를 model에 저장 (template에서 값을 사용하기 위함)
		model.addAttribute("paging", paging);

		return "questionList";
	}

	// 질문 상세 페이지
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerDTO answerDTO) {

		/*
			요청 URL에 변수 삽입 시 @PathVariable 어노테이션 사용
			- Mapping value 값에 사용한 변수와 PathVariable에 사용한 매개변수 이름이 같아야 함
        */

		// id에 해당하는 질문을 가져온 뒤 model 객체를 사용하여 template에 전달
		Question question = this.service.getQuestion(id);
		model.addAttribute("question", question);

		return "questionDetail";
	}

	// 글쓰기 페이지
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate(QuestionDTO questionDTO) {

		return "questionForm";
	}

	// 작성한 질문 저장 및 리스트 페이지로 리다이렉트
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionDTO questionDTO, BindingResult bindingResult,
	                             Principal principal) {

		// validation 검증 후 에러 발생 시 실행
		if(bindingResult.hasErrors()) {

			return "questionForm";
		}

		SiteUser siteUser = userService.getUser(principal.getName());

		// service를 통해 DTO가 가지고 있는 제목, 내용, 작성자 데이터를 DB에 저장
		service.create(questionDTO.getSubject(), questionDTO.getContent(), siteUser);

		// 질문 저장 후 리스트로 이동
		return "redirect:/question/list";
	}
}

/*
	BindingResult
	- Validation 검증이 수행 된 결과 반환
	- 반드시 @Valid 매개변수 뒤에 위치
	  (위치가 맞지 않을 때 @Valid만 적용, 검증 실패 시 400 error 발생)

	@PreAuthorize("isAuthenticated()")
	- 로그인한 사용자만 호출
	- 해당 어노테이션이 적용 된 페이지를 로그아웃 상태에서 접근 시
	  로그인 페이지로 강제 이동
 */