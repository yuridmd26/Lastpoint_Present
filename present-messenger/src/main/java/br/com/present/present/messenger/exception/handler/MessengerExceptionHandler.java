package br.com.present.present.messenger.exception.handler;

import br.com.present.commons.util.PresentDataUtils;
import br.com.present.commons.util.PresentInternationalizationUtils;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.present.messenger.exception.ApiQueueNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessengerExceptionHandler {

    @ExceptionHandler(ApiQueueNotExist.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleQueueNotExist(ApiQueueNotExist exception){
        ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
                new String[]{PresentInternationalizationUtils.getMessage(exception.getMessage(), exception.getArgs())},
                HttpStatus.SERVICE_UNAVAILABLE,
                PresentDataUtils.getZonedDateTimeNow()
        );
        return new ResponseEntity<>(apiException, HttpStatus.SERVICE_UNAVAILABLE);
    }
}