package br.com.present.auths.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = -3983192808514635545L;
	
	private String nome;
	private String login;
	private String senha;
	private String email;
	
	public UserDTO(String nome, String login) {
		this.nome = nome;
		this.login = login;
	}
	
}