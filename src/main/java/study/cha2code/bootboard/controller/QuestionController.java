package study.cha2code.bootboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.repository.QuestionRepository;
import study.cha2code.bootboard.service.QuestionService;

import java.util.List;

/**
 * Question list를 매핑하기 위한 controller
 */
@Controller
// final이 붙은 속성을 생성자로 객체 주입 (= QuestionService)
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

	private final QuestionService service;

	@GetMapping("/list")
	public String list(Model model) {

		// QuestionService에서 질문 목록 데이터 가져오기
		List<Question> questionList = this.service.getList();
		// questionList를 model에 저장 (template에서 값을 사용하기 위함)
		model.addAttribute("questionList", questionList);

		return "questionList";
	}
}