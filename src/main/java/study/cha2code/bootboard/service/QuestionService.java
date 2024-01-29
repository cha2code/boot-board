package study.cha2code.bootboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.repository.QuestionRepository;

import java.util.List;

/**
 * QuestionController와 QuestionRepository간 로직 처리를 위한 service
 */
@Service
// final이 붙은 속성을 생성자로 주입 (= QuestionRepository)
@RequiredArgsConstructor
public class QuestionService {

	// 생성자 주입
	private final QuestionRepository repository;

	// 질문 목록을 가져오는 메소드
	public List<Question> getList() {

		// QuestionRepository를 통해 전체 질문 목록 가져온 list 반환
		return this.repository.findAll();
	}
}
