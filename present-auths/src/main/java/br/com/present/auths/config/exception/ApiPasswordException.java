package br.com.present.auths.config.exception;

import br.com.present.commons.exception.ApiRuntimeException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiPasswordException extends ApiRuntimeException {

	@Serial
	private static final long serialVersionUID = 3731150474065927517L;

	public ApiPasswordException(String message) {
		super(message);
	}

	public ApiPasswordException(String message, Object[] args) {
		super(message, args);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}