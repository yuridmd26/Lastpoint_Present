package br.com.present.users.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.commons.exception.handler.ApiExceptionHandler;
import br.com.present.users.exception.ApiUserLoginExistException;

@ControllerAdvice
public class UserExceptionHandler extends ApiExceptionHandler {
	
	@ExceptionHandler(ApiUserLoginExistException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handleUserAlreadyExistException(ApiUserLoginExistException exception){
		return handlerDefaultResponse(exception);
	}
	
}