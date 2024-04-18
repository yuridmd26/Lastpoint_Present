package br.com.present.auths.config.exception;

import br.com.present.commons.exception.ApiRuntimeException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiLoginException extends ApiRuntimeException {

	@Serial
	private static final long serialVersionUID = 3731150474065927517L;

	public ApiLoginException(String message) {
		super(message);
	}

	public ApiLoginException(String message, Object[] args) {
		super(message, args);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}