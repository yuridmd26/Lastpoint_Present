package br.com.present.auths.config.exception.handler;

import br.com.present.auths.config.exception.ApiAuthenticationException;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.commons.exception.handler.ApiExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler extends ApiExceptionHandler {

	@ExceptionHandler(ApiAuthenticationException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handleApiAuthenticationException(ApiAuthenticationException exception){
		return handlerDefaultResponse(exception);
	}
	
}