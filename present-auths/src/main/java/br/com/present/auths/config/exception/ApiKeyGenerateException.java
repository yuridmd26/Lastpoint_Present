package br.com.present.auths.config.exception;

import org.springframework.http.HttpStatus;

import br.com.present.commons.exception.ApiRuntimeException;

import java.io.Serial;

public class ApiKeyGenerateException extends ApiRuntimeException {

	@Serial
	private static final long serialVersionUID = -5200445762025424184L;

	public ApiKeyGenerateException(String message) {
        super(message);
    }

    public ApiKeyGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}