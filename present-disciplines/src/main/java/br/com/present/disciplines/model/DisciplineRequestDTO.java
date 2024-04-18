package br.com.present.disciplines.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@Schema(name = "Discipline Request")
public class DisciplineRequestDTO {
	
	@NotBlank(message = "disciplineRequestDTO.err.notBlankCode")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message="disciplineRequestDTO.err.patternCode")
    @Length(max = 50, message = "userRequestDTO.err.maxLengthCode")
    private String code;

    @NotBlank(message = "disciplineRequestDTO.err.notBlankName")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message="disciplineRequestDTO.err.patternName")
    @Length(max = 100, message = "userRequestDTO.err.maxLengthName")
    private String name;

    @Length(max = 255, message = "userRequestDTO.err.maxLengthDescription")
    private String description;
    
}