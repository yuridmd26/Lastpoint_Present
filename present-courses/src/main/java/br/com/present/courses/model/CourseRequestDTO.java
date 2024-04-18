package br.com.present.courses.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@Schema(name = "Course Request")
public class CourseRequestDTO {
	
	@NotBlank(message = "courseRequestDTO.err.notBlankCode")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message="courseEntity.err.patternCode")
	@Length(max = 50, message = "courseRequestDTO.err.maxLengthCode")
    private String code;
	
	@NotBlank(message = "courseRequestDTO.err.notBlankName")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message="courseEntity.err.patternName")
	@Length(max = 100, message = "courseRequestDTO.err.maxLengthName")
    private String name;

	@Length(max = 255, message = "courseRequestDTO.err.maxLengthDescription")
    private String description;

}