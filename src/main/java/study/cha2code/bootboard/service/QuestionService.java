package study.cha2code.bootboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.exception.DataNotFoundException;
import study.cha2code.bootboard.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

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

	// id 값으로 질문 데이터를 조회하는 메소드
	public Question getQuestion(Integer id) {

		// repository에서 id로 해당 질문 검색
		Optional<Question> question = this.repository.findById(id);

		// id 값이 존재하는 지 검사 후 질문 내용 반환
		if(question.isPresent()) {

			return question.get();
		}

		// id 값이 존재하지 않을 경우
		else {

			throw new DataNotFoundException("question not found");
		}
	}
}
