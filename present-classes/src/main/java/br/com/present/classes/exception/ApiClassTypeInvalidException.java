package br.com.present.classes.exception;

import br.com.present.commons.exception.ApiRuntimeException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiClassTypeInvalidException extends ApiRuntimeException {

    @Serial
    private static final long serialVersionUID = -5577651392429167855L;

    public ApiClassTypeInvalidException(String message) {
        super(message);
    }

    public ApiClassTypeInvalidException(String message, Object[] args) {
        super(message, args);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
