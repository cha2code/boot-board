package study.cha2code.bootboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.service.AnswerService;
import study.cha2code.bootboard.service.QuestionService;

/**
 * 질문에 대한 답변을 mapping 하기 위한 controller
 */
@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;

	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id,
	                           @RequestParam(value = "content") String content) {

		Question question = this.questionService.getQuestion(id);

		// 작성한 답변 저장
		this.answerService.create(question, content);

		// 문자열 반환 메소드 (%s 에는 id 값 삽입)
		return String.format("redirect:/question/detail/%s", id);
	}
}
