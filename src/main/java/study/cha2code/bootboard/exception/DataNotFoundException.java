package study.cha2code.bootboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 특정 entity나 데이터를 찾을 수 없을 때 실행되는 exception 클래스
 * 상태 코드, 이유를 포함한 응답 생성 후 클라이언트에게 반환
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {

	// 역직렬화 시 해당 클래스의 버전이 맞는 지 확인
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message) {

		super(message);
	}
}
