package br.com.present.events.exception;

import br.com.present.commons.exception.ApiRuntimeException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiRecordDateConflictEventException extends ApiRuntimeException {

	@Serial
	private static final long serialVersionUID = -5577651392429167855L;

	public ApiRecordDateConflictEventException(String message) {
        super(message);
    }

	public ApiRecordDateConflictEventException(String message, Object[] args) {
        super(message, args);
    }
	
	@Override
	public HttpStatus getStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}
}
