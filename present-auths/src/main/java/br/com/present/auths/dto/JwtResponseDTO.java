package br.com.present.auths.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDTO {

	private String token;
	
}