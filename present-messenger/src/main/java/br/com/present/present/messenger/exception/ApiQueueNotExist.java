package br.com.present.present.messenger.exception;

import br.com.present.commons.exception.ApiRuntimeException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiQueueNotExist extends ApiRuntimeException {

    @Serial
    private static final long serialVersionUID = -5577651392429167855L;

    public ApiQueueNotExist(String message) {
        super(message);
    }

    public ApiQueueNotExist(String message, Object[] args) {
        super(message, args);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.SERVICE_UNAVAILABLE;
    }
}
