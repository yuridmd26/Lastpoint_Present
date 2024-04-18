package br.com.present.attendances.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Attendance Request")
public class AttendanceRequestDTO {

    @NotNull(message = "attendanceRequestDTO.err.notNullUserId")
	@Min(value = 1, message="attendanceRequestDTO.err.minLengthUserId")
    private Long userId;

    @NotNull(message = "attendanceRequestDTO.err.notNullEventId")
	@Min(value = 1, message="attendanceRequestDTO.err.minLengthEventId")
    private Long eventId;

}