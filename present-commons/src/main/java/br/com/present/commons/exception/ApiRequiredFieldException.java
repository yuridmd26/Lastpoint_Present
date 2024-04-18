package br.com.present.commons.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiRequiredFieldException extends ApiRuntimeException {

	@Serial
	private static final long serialVersionUID = -5577651392429167855L;

	public ApiRequiredFieldException(String message) {
        super(message);
    }
	
	public ApiRequiredFieldException(String message, Object[] args) {
        super(message, args);
    }

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}