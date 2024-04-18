package br.com.present.events.exception.handler;

import br.com.present.events.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.commons.exception.handler.ApiExceptionHandler;

@ControllerAdvice
public class EventExceptionHandler extends ApiExceptionHandler {

	@ExceptionHandler(ApiEventCodeAlreadyExistException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleEventCodeAlreadyExistException(ApiEventCodeAlreadyExistException exception){
        return handlerDefaultResponse(exception);
    }

    @ExceptionHandler(ApiUserEventAlreadyExistException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleUserEventAlreadyExistException(ApiUserEventAlreadyExistException exception){
        return handlerDefaultResponse(exception);
    }

    @ExceptionHandler(ApiRecordDateConflictEventException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleRecordDateConflictEventException(ApiRecordDateConflictEventException exception){
        return handlerDefaultResponse(exception);
    }

    @ExceptionHandler(ApiRecordDateConflictUserEventException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleRecordDateConflictUserEventException(ApiRecordDateConflictUserEventException exception){
        return handlerDefaultResponse(exception);
    }

    @ExceptionHandler(ApiStartNotGteEndDateEventException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleStartNotGteEndDateEventException(ApiStartNotGteEndDateEventException exception){
        return handlerDefaultResponse(exception);
    }
}