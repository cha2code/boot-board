package study.cha2code.bootboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.cha2code.bootboard.entity.Answer;

/**
 * 답변 저장을 위한 repository
 */
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
