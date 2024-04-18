package br.com.present.courses.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;

import br.com.present.commons.exception.ApiRuntimeException;

public class ApiCourseCodeAlreadyExistException extends ApiRuntimeException {

	@Serial
	private static final long serialVersionUID = -5577651392429167855L;

	public ApiCourseCodeAlreadyExistException(String message) {
        super(message);
    }
	
	public ApiCourseCodeAlreadyExistException(String message, Object[] args) {
        super(message, args);
    }
	
	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
