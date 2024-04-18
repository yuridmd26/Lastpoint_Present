package br.com.present.classes.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Class Course Response")
public class ClassCourseResponseDTO {
	
	private Long id;
    private Long courseId;
    private Long classId;

}