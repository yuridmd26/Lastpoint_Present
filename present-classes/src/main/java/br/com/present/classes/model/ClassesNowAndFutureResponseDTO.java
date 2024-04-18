package br.com.present.classes.model;

import java.time.OffsetDateTime;

import br.com.present.classes.type.ClassStatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Schema(name = "Classes Prof Response")
public class ClassesNowAndFutureResponseDTO {

    private Long id;
    private String name;
    private String description;
    private OffsetDateTime startDatetime;
    private OffsetDateTime endDatetime;
    private String teacherName;

    @Setter
    private ClassStatusType status;

    private String disciplineName;
    private String groupName;

}
