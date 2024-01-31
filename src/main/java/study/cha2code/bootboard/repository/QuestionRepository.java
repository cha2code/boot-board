package study.cha2code.bootboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import study.cha2code.bootboard.entity.Question;

import java.util.List;

/**
 * Question 테이블 데이터의 CRUD를 위한 인터페이스
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	// 유형별 찾은 객체를 리턴하는 메소드들
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);

	// 페이징을 위한 메소드
	Page<Question> findAll(Pageable pageable);
}

/*
JpaRepository
- CRUD 작업을 처리하는 메소드들이 내장되어 있는 인터페이스
- 여기에서는 Question entity로 repository를 생성
  (기본 키의 타입은 Integer)
 */