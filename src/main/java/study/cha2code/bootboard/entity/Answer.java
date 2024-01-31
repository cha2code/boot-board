package study.cha2code.bootboard.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 답변 Entity 정의 클래스
 */
@Data
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime createDate;

	// Question entity와 연결 된 속성 (외래키, N:1 관계)
	@ManyToOne
	private Question question;

	// 작성자 (한 명이 여러 답변 작성이 가능하기 때문에 N:1 관계)
	@ManyToOne
	private SiteUser author;
}
