package study.cha2code.bootboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.cha2code.bootboard.entity.Answer;
import study.cha2code.bootboard.entity.Question;
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
	public void create(Question question, String content) {

		Answer answer = new Answer();

		// 내용, 시간, question 속성 값 저장
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		this.repository.save(answer);
	}
}
