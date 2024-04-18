package br.com.present.classes.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(name = "Class Request")
public class ClassRequestDTO {

    @NotNull(message = "classRequestDTO.err.notNullGroupId")
    @Min(value = 1, message="classRequestDTO.err.minLengthGroupId")
    private Long groupId;

    @NotNull(message = "classRequestDTO.err.notNullDisciplineId")
    @Min(value = 1, message="classRequestDTO.err.minLengthDisciplineId")
    private Long disciplineId;

    @NotNull(message = "classRequestDTO.err.notNullEventId")
    @Min(value = 1, message="classRequestDTO.err.minLengthEventId")
    private Long eventId;

}