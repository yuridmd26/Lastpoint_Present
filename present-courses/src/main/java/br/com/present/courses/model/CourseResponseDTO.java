package br.com.present.courses.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Course Response")
public class CourseResponseDTO {

	private Long id;
    private String code;
    private String name;
    private String description;
	
}