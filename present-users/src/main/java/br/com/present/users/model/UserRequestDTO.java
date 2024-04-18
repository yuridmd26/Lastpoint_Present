package br.com.present.users.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@Schema(name = "User Request")
public class UserRequestDTO {

	@NotBlank(message = "userRequestDTO.err.notBlankCode")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message="userRequestDTO.err.patternCode")
    @Length(max = 50, message = "userRequestDTO.err.maxLengthCode")
    private String code;
	
	@NotBlank(message = "userRequestDTO.err.notBlankName")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message="userRequestDTO.err.patternName")
    @Length(max = 100, message = "userRequestDTO.err.maxLengthName")
    private String name;
	
	@NotBlank(message = "userRequestDTO.err.notBlankLogin")
    @Length(max = 100, message = "userRequestDTO.err.maxLengthLogin")
    private String login;

	@NotBlank(message = "userRequestDTO.err.notBlankPassword")
    @Length(max = 100, message = "userRequestDTO.err.maxLengthPassword")
    private String password;
	
    @NotBlank(message = "userRequestDTO.err.notBlankType")
    @Length(max = 50, message = "userRequestDTO.err.maxLengthType")
    private String type;
	
}
