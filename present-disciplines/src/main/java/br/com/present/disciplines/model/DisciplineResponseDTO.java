package br.com.present.disciplines.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Discipline Response")
public class DisciplineResponseDTO {
	
    private Long id;
    private String code;
    private String name;
    private String description;

}
