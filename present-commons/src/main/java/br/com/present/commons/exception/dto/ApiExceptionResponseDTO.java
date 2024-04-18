package br.com.present.commons.exception.dto;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(name = "Exception Response")
public class ApiExceptionResponseDTO {
	
	private final String[] messages;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timestamp;

}