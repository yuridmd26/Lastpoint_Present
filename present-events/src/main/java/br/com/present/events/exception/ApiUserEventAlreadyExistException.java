package br.com.present.events.exception;

import br.com.present.commons.exception.ApiRuntimeException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiUserEventAlreadyExistException extends ApiRuntimeException {

    @Serial
    private static final long serialVersionUID = -5577651392429167855L;

    public ApiUserEventAlreadyExistException(String message) {
        super(message);
    }

    public ApiUserEventAlreadyExistException(String message, Object[] args) {
        super(message, args);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
