package study.cha2code.bootboard.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 질문 entity 정의 클래스
 */
@Data
@Entity
public class Question {

	// 기본 키
	@Id
	// 해당 속성 값을 자동으로 1씩 증가하여 저장
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// column의 길이를 200으로 설정
	@Column(length = 200)
	private String subject;

	// text를 column의 데이터로 저장 (글자 수를 제한할 수 없을 때 사용)
	@Column(columnDefinition = "TEXT")
	private String content;

	// 생성일시
	private LocalDateTime createDate;

	// Question은 Answer와 1:N 관계
	// 질문이 삭제 되면 답변들도 함께 삭제
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;

	// 작성자 (한 명이 여러 질문 작성이 가능하기 때문에 N:1 관계)
	@ManyToOne
	private SiteUser author;
}
