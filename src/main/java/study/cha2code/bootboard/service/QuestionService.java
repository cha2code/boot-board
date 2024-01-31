package study.cha2code.bootboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import study.cha2code.bootboard.entity.Question;
import study.cha2code.bootboard.entity.SiteUser;
import study.cha2code.bootboard.exception.DataNotFoundException;
import study.cha2code.bootboard.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
	public Page<Question> getList(int page) {

		// 게시 글을 최신 순으로 정렬
		List<Sort.Order> sorts = new ArrayList<>();
		// 정렬 조건 (추가 시 sorts.add() 메서드 사용 가능)
		sorts.add(Sort.Order.desc("createDate"));

		// 한 페이지에 출력할 글 개수 설정
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		// 페이징한 객체 반환
		return this.repository.findAll(pageable);
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

	// 질문 내용 저장
	public void create(String subject, String content, SiteUser user) {

		Question question = new Question();

		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		question.setAuthor(user);
		repository.save(question);
	}
}
