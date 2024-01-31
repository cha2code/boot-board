package study.cha2code.bootboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.repository.QuestionRepository;
import study.cha2code.bootboard.service.QuestionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JpaRepository가 제공하는 메소드들을 테스트하는 클래스
 */
@SpringBootTest
class BootBoardApplicationTests {

	@Autowired
	private QuestionService service;

	@Test
	void testJPA() {

		for(int i = 1; i <= 300; i++) {

			String subject = String.format("테스트 데이터 : [%03d]", i);
			String content = "내용 없음";
			this.service.create(subject, content, null);
		}
	}

}
