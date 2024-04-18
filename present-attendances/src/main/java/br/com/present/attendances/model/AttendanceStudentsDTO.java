package br.com.present.attendances.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Attendance Student")
public class AttendanceStudentsDTO
{
    String studentCode;
    String studentName;
    Boolean status;
}
