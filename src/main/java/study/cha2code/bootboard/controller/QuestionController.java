package study.cha2code.bootboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Question list를 매핑하기 위한 controller
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

	@GetMapping("/list")
	// JSON 형식으로 응답해주는 어노테이션
	@ResponseBody
	public String list() {
		return "question list";
	}
}