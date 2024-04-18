package br.com.present.attendances.exception;

import br.com.present.commons.exception.ApiRuntimeException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiAttendanceAlreadyExistException extends ApiRuntimeException {

    @Serial
    private static final long serialVersionUID = -5577651392429167855L;

    public ApiAttendanceAlreadyExistException(String message) {
        super(message);
    }

    public ApiAttendanceAlreadyExistException(String message, Object[] args) {
        super(message, args);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
