package br.com.present.commons.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract class ApiRuntimeException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -5759178538698381779L;
	
	private final transient Object[] args;

	protected ApiRuntimeException(String message) {
		super(message);
		this.args = new Object[] {};
    }
	
	protected ApiRuntimeException(String message, Object[] args) {
		super(message);
		this.args = args;
    }

	protected ApiRuntimeException(Throwable cause) {
		super(cause);
		this.args = new Object[] {};
	}
	
	protected ApiRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.args = new Object[] {};
    }

	public abstract HttpStatus getStatus();
	
}