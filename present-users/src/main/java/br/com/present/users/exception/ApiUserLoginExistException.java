package br.com.present.users.exception;

import org.springframework.http.HttpStatus;

import br.com.present.commons.exception.ApiRuntimeException;

import java.io.Serial;

public class ApiUserLoginExistException extends ApiRuntimeException {
	
	@Serial
	private static final long serialVersionUID = -5577651392429167855L;

	public ApiUserLoginExistException(String message) {
        super(message);
    }
	
	public ApiUserLoginExistException(String message, Object[] args) {
        super(message, args);
    }

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
