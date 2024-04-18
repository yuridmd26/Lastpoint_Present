package br.com.present.groups.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@Schema(name = "Group Request")
public class GroupRequestDTO {
	
	@NotBlank(message = "groupRequestDTO.err.notBlankCode")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message="groupRequestDTO.err.patternCode")
	@Length(max = 50, message = "groupRequestDTO.err.maxLengthCode")
    private String code;

	@NotBlank(message = "groupRequestDTO.err.notBlankName")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message="groupRequestDTO.err.patternName")
	@Length(max = 100, message = "groupRequestDTO.err.maxLengthName")
    private String name;

	@Length(max = 255, message = "groupRequestDTO.err.maxLengthDescription")
	private String description;
}
