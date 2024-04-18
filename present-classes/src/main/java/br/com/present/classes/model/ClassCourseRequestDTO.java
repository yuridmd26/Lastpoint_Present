package br.com.present.classes.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(name = "Class Course Request")
public class ClassCourseRequestDTO {

    @NotNull(message = "classCourseRequestDTO.err.notNullCourseId")
    @Min(value = 1, message="classCourseRequestDTO.err.minLengthCourseId")
    private Long courseId;

    @NotNull(message = "classCourseRequestDTO.err.notNullClassId")
    @Min(value = 1, message="classCourseRequestDTO.err.minLengthClassId")
    private Long classId;

}