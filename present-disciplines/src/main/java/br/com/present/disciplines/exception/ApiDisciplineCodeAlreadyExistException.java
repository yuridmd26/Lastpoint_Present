package br.com.present.disciplines.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;

import br.com.present.commons.exception.ApiRuntimeException;

public class ApiDisciplineCodeAlreadyExistException extends ApiRuntimeException {

	@Serial
	private static final long serialVersionUID = -5577651392429167855L;

	public ApiDisciplineCodeAlreadyExistException(String message) {
        super(message);
    }
	
	public ApiDisciplineCodeAlreadyExistException(String message, Object[] args) {
        super(message, args);
    }
	
	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
