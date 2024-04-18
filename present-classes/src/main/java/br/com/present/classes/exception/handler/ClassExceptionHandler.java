package br.com.present.classes.exception.handler;

import br.com.present.classes.exception.ApiClassAlreadyExistException;
import br.com.present.classes.exception.ApiClassCourseAlreadyExistException;
import br.com.present.classes.exception.ApiClassTypeInvalidException;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import br.com.present.commons.exception.handler.ApiExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClassExceptionHandler extends ApiExceptionHandler {

    @ExceptionHandler(ApiClassAlreadyExistException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleClassAlreadyExistException(ApiClassAlreadyExistException exception){
        return handlerDefaultResponse(exception);
    }

    @ExceptionHandler(ApiClassCourseAlreadyExistException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleClassCourseAlreadyExistException(ApiClassCourseAlreadyExistException exception){
        return handlerDefaultResponse(exception);
    }

    @ExceptionHandler(ApiClassTypeInvalidException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleClassTypeInvalidException(ApiClassTypeInvalidException exception){
        return handlerDefaultResponse(exception);
    }
}