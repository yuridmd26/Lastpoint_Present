package br.com.present.auths.config.exception;

import org.springframework.http.HttpStatus;

import br.com.present.commons.exception.ApiRuntimeException;

import java.io.Serial;

public class ApiAuthenticationException extends ApiRuntimeException {

	@Serial
	private static final long serialVersionUID = 3731150474065927517L;

	public ApiAuthenticationException(String message) {
		super(message);
	}

	public ApiAuthenticationException(String message, Object[] args) {
		super(message, args);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.UNAUTHORIZED;
	}

}