package br.com.present.classes.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Class Response")
public class ClassResponseDTO {
	
	private Long id;
    private Long groupId;
    private Long disciplineId;
    private Long eventId;

}