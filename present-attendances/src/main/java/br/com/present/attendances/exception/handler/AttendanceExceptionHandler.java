package br.com.present.attendances.exception.handler;

import br.com.present.attendances.exception.ApiAttendanceAlreadyExistException;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import br.com.present.commons.exception.handler.ApiExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AttendanceExceptionHandler extends ApiExceptionHandler {

    @ExceptionHandler(ApiAttendanceAlreadyExistException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleAttendanceAlreadyExistException(ApiAttendanceAlreadyExistException exception){
        return handlerDefaultResponse(exception);
    }
}