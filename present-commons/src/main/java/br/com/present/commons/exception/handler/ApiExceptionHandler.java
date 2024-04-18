package br.com.present.commons.exception.handler;

import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;

import br.com.present.commons.util.*;
import com.google.gson.JsonIOException;
import feign.FeignException;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.AmqpException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.present.commons.exception.ApiRequiredFieldException;
import br.com.present.commons.exception.ApiRuntimeException;
import br.com.present.commons.exception.NoSuchRecordException;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.commons.exception.keys.BaseExceptionKeys;

@ControllerAdvice
public class ApiExceptionHandler {

	protected String handlerMessage(ApiRuntimeException exception) {
		return PresentInternationalizationUtils.getMessage(exception.getMessage(), exception.getArgs());
	}

	protected <T extends ApiRuntimeException> ResponseEntity<ApiExceptionResponseDTO> handlerDefaultResponse(T exception){
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
			new String[]{handlerMessage(exception)},
			exception.getStatus(),
			PresentDataUtils.getZonedDateTimeNow()
		);
		return new ResponseEntity<>(apiException, exception.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerException(Exception exception){
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
			new String[]{exception.getMessage()},
			HttpStatus.INTERNAL_SERVER_ERROR,
			PresentDataUtils.getZonedDateTimeNow()
		);
		return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleTypeMismatchException() {
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
			new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.TYPE_MISMATCH)},
			HttpStatus.BAD_REQUEST,
			PresentDataUtils.getZonedDateTimeNow()
		);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleMethodNotSupportedException() {
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
			new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.METHOD_NOT_ALLOWED)},
			HttpStatus.METHOD_NOT_ALLOWED,
			PresentDataUtils.getZonedDateTimeNow()
		);
        return new ResponseEntity<>(apiException, HttpStatus.METHOD_NOT_ALLOWED);
    }

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleMediaTypeNotSupportedException() {
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
			new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.MEDIA_TYPE_NOT_SUPPORTED)},
			HttpStatus.UNSUPPORTED_MEDIA_TYPE,
			PresentDataUtils.getZonedDateTimeNow()
		);
        return new ResponseEntity<>(apiException, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleMessageNotReadableException() {
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
			new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.MESSAGE_NOT_READABLE)},
			HttpStatus.BAD_REQUEST,
			PresentDataUtils.getZonedDateTimeNow()
		);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponseDTO> handleValidationExceptions(MethodArgumentNotValidException exception) {
		List<String> lMessages = new ArrayList<>();
		PresentStreamUtils.collectionToStream(exception.getBindingResult().getAllErrors())
				   		  .forEach(error -> lMessages.add(PresentInternationalizationUtils.getMessage(error.getDefaultMessage())));

		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
				!lMessages.isEmpty() ? lMessages.toArray(new String[0]) : new String[]{exception.getMessage()},
				HttpStatus.BAD_REQUEST,
				PresentDataUtils.getZonedDateTimeNow()
		);
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(ApiRequiredFieldException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerRequiredFieldException(ApiRequiredFieldException exception){
		return handlerDefaultResponse(exception);
	}

	@ExceptionHandler(NoSuchRecordException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerNoSuchRecordException(NoSuchRecordException exception){
		return handlerDefaultResponse(exception);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerDataIntegrityViolationException(DataIntegrityViolationException exception){
		return DataIntegrityViolationExceptionHandler.handlerDataIntegrityViolationException(exception);
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerFeignException(FeignException exception){
		return FeignExceptionHandler.handleFeignException(exception);
	}

	@ExceptionHandler(QueryTimeoutException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerQueryTimeoutException(){
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
				new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.QUERY_TIMEOUT)},
				HttpStatus.REQUEST_TIMEOUT,
				PresentDataUtils.getZonedDateTimeNow()
		);
		return new ResponseEntity<>(apiException, HttpStatus.REQUEST_TIMEOUT);
	}

	@ExceptionHandler(SQLTimeoutException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerSQLTimeoutException(){
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
				new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.SQL_CONNECT_TIMEOUT)},
				HttpStatus.GATEWAY_TIMEOUT,
				PresentDataUtils.getZonedDateTimeNow()
		);
		return new ResponseEntity<>(apiException, HttpStatus.GATEWAY_TIMEOUT);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerMissingServletRequestParameterException(){
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
				new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.MISSING_SERVLET_REQUEST)},
				HttpStatus.BAD_REQUEST,
				PresentDataUtils.getZonedDateTimeNow()
		);
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JsonIOException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerJsonIOException(){
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
				new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.JSON_CONVERT_ERROR)},
				HttpStatus.INTERNAL_SERVER_ERROR,
				PresentDataUtils.getZonedDateTimeNow()
		);
		return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AmqpException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerAmqpException(AmqpException exception){
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
				new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.AMQP_ERROR,
						new String[]{exception.getMessage()})},
				HttpStatus.INTERNAL_SERVER_ERROR,
				PresentDataUtils.getZonedDateTimeNow()
		);
		return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AmqpConnectException.class)
	public ResponseEntity<ApiExceptionResponseDTO> handlerAmqpConnectException(){
		ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
				new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.AMQP_CONNECT_ERROR)},
				HttpStatus.SERVICE_UNAVAILABLE,
				PresentDataUtils.getZonedDateTimeNow()
		);
		return new ResponseEntity<>(apiException, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
