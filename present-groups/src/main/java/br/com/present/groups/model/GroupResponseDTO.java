package br.com.present.groups.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Group Response")
public class GroupResponseDTO {
	
    private Long id;
    private String code;
    private String name;
    private String description;

}
