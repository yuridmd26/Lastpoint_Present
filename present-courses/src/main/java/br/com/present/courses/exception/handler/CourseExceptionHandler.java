package br.com.present.courses.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.commons.exception.handler.ApiExceptionHandler;
import br.com.present.courses.exception.ApiCourseCodeAlreadyExistException;

@ControllerAdvice
public class CourseExceptionHandler extends ApiExceptionHandler {
	@ExceptionHandler(ApiCourseCodeAlreadyExistException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleCourseCodeAlreadyExistException(ApiCourseCodeAlreadyExistException exception){
        return handlerDefaultResponse(exception);
    }
}