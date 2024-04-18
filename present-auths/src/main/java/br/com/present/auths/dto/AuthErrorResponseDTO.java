package br.com.present.auths.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthErrorResponseDTO {

	private String timestamp;
	private Integer status;
	private String error;
	private String message;
	
}