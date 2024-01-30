package study.cha2code.bootboard.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 질문 제목, 내용 값 전달 및 검증하는 DTO
 */
@Data
public class QuestionDTO {

	@NotEmpty(message = "제목은 필수 항목입니다.")
	@Size(max = 20)
	private String subject;

	@NotEmpty(message = "내용은 필수 항목입니다.")
	private String content;
}
