package br.com.present.users.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "User Response")
public class UserResponseDTO {

	private Long id;
    private String code;
    private String name;
    private String login;
    private String type;
	
}
