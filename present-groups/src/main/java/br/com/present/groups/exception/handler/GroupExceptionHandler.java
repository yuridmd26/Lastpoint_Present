package br.com.present.groups.exception.handler;

import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.groups.exception.ApiGroupCodeAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import br.com.present.commons.exception.handler.ApiExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GroupExceptionHandler extends ApiExceptionHandler {

    @ExceptionHandler(ApiGroupCodeAlreadyExistException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleGroupCodeAlreadyExistException(ApiGroupCodeAlreadyExistException exception){
        return handlerDefaultResponse(exception);
    }

}