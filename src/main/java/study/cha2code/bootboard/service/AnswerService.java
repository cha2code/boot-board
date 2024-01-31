package study.cha2code.bootboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.cha2code.bootboard.entity.Answer;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.entity.SiteUser;
import study.cha2code.bootboard.repository.AnswerRepository;

import java.time.LocalDateTime;

/**
 * 답변을 저장하는 Service
 */
@RequiredArgsConstructor
@Service
public class AnswerService {

	// 생성자 주입
	private final AnswerRepository repository;

	// 답변 생성 메소드
	public void create(Question question, String content, SiteUser author) {

		Answer answer = new Answer();

		// 각 속성 값 저장
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		repository.save(answer);
	}
}
