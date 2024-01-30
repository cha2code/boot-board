package study.cha2code.bootboard.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 댓글 내용 값을 전달하는 DTO
 */
@Data
public class AnswerDTO {

	@NotEmpty(message = "내용은 필수항목입니다.")
	private String content;
}
