package study.cha2code.bootboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.service.QuestionService;

import java.util.List;

/**
 * 질문 게시판 mapping을 위한 controller
 */
@Controller
// final이 붙은 속성을 생성자로 객체 주입 (= QuestionService)
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

	// 생성자 주입
	private final QuestionService service;

	// 질문 게시판 페이지
	@GetMapping("/list")
	public String list(Model model) {

		// QuestionService에서 질문 목록 데이터 가져오기
		List<Question> questionList = this.service.getList();
		// questionList를 model에 저장 (template에서 값을 사용하기 위함)
		model.addAttribute("questionList", questionList);

		return "questionList";
	}

	// 질문 상세 페이지
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {

		/*
			요청 URL에 변수 삽입 시 @PathVariable 어노테이션 사용
			- Mapping value 값에 사용한 변수와 PathVariable에 사용한 매개변수 이름이 같아야 함
        */

		// id에 해당하는 질문을 가져온 뒤 model 객체를 사용하여 template에 전달
		Question question = this.service.getQuestion(id);
		model.addAttribute("question", question);

		return "questionDetail";
	}
}