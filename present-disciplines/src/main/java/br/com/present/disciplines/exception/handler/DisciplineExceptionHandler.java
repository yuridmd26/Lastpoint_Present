package br.com.present.disciplines.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.commons.exception.handler.ApiExceptionHandler;
import br.com.present.disciplines.exception.ApiDisciplineCodeAlreadyExistException;

@ControllerAdvice
public class DisciplineExceptionHandler extends ApiExceptionHandler {

    @ExceptionHandler(ApiDisciplineCodeAlreadyExistException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleDisciplineCodeAlreadyExistException(ApiDisciplineCodeAlreadyExistException exception){
        return handlerDefaultResponse(exception);
    }
}