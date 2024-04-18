package br.com.present.commons.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class NoSuchRecordException extends ApiRuntimeException {

	@Serial
	private static final long serialVersionUID = -5577651392429167855L;

	public NoSuchRecordException(String message) {
        super(message);
    }
	
	public NoSuchRecordException(String message, Object[] args) {
        super(message, args);
    }

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
}