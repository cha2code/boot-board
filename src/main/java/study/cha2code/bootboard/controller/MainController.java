package study.cha2code.bootboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인 페이지 접속 시 질문 게시판으로 redirect하는 controller
 */
@Controller
public class MainController {

	@GetMapping("/")
	public String root() {

		return "redirect:/question/list";
	}
}
