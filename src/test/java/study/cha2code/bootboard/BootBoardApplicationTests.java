package study.cha2code.bootboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JpaRepository가 제공하는 메소드들을 테스트하는 클래스
 */
@SpringBootTest
class BootBoardApplicationTests {

	// 생성자 주입
	private QuestionRepository repository;
	@Autowired
	public BootBoardApplicationTests(QuestionRepository repository){
		this.repository = repository;
	}

	// Question 테이블에 데이터를 저장하는 테스트
	/*@Test
	void testJPA() {

		Question firstQuestion = new Question();
		firstQuestion.setSubject("boot board는 무엇인가요?");
		firstQuestion.setContent("boot board에 대해 알고 싶어요.");
		firstQuestion.setCreateDate(LocalDateTime.now());
		this.repository.save(firstQuestion);

		Question secondQuestion = new Question();
		firstQuestion.setSubject("boot 질문입니다.");
		firstQuestion.setContent("id는 자동으로 생성되나요?");
		firstQuestion.setCreateDate(LocalDateTime.now());
		this.repository.save(secondQuestion);
	}*/

	// Question 테이블에 저장 된 모든 데이터를 조회하는 테스트
	/*@Test
	void testJPA() {

		List<Question> all = this.repository.findAll();
		assertEquals(2, all.size());

		Question question = all.get(0);
		assertEquals("boot board는 무엇인가요?", question.getSubject());
	}*/

	/*
	// Question entity의 id 값으로 조회
	@Test
	void testJPA() {

		// findById 값이 null일 경우 처리하기 위해 Optional 사용
		Optional<Question> optionalQuestion = this.repository.findById(1);

		// findById로 값을 찾았을 경우
		if(optionalQuestion.isPresent()) {
			// get()을 통해 Question 값을 얻음
			Question question = optionalQuestion.get();
			assertEquals("boot board는 무엇인가요?", question.getSubject());
		}
	}*/

	// subject 값으로 테이블 조회
	@Test
	void testJPA() {

		Question question = this.repository.findBySubject("boot board는 무엇인가요?");
		assertEquals(1, question.getId());
	}

}
