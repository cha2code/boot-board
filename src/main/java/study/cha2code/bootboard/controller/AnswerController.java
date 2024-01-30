package study.cha2code.bootboard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.cha2code.bootboard.dto.AnswerDTO;
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
	                           @Valid AnswerDTO answerDTO, BindingResult bindingResult) {

		//
		Question question = this.questionService.getQuestion(id);

		// validation 검증 후 에러 발생 시 실행
		if(bindingResult.hasErrors()) {

			// Question 객체 전달
			model.addAttribute("question", question);

			return "questionDetail";
		}

		// 작성한 답변 저장
		this.answerService.create(question, answerDTO.getContent());

		// 문자열 반환 메소드 (%s 에는 id 값 삽입)
		return String.format("redirect:/question/detail/%s", id);
	}
}
