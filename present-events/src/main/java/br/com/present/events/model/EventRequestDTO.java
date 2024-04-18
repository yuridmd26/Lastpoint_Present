package br.com.present.events.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@Schema(name = "Event Request")
public class EventRequestDTO {
	
	@NotBlank(message = "eventRequestDTO.err.notBlankCode")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message="eventRequestDTO.err.patternCode")
    @Length(max = 50, message = "eventRequestDTO.err.maxLengthCode")
    private String code;

    @NotBlank(message = "eventRequestDTO.err.notBlankName")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message="eventRequestDTO.err.patternName")
    @Length(max = 100, message = "eventRequestDTO.err.maxLengthName")
    private String name;

    @Length(max = 255, message = "eventRequestDTO.err.maxLengthDescription")
    private String description;

    @NotNull(message = "eventRequestDTO.err.notNullStartDateTime")
    private ZonedDateTime startDateTime;

    @NotNull(message = "eventRequestDTO.err.notNullEndDateTime")
    private ZonedDateTime endDateTime;

    @NotNull(message = "eventRequestDTO.err.notNullUserId")
    @Min(value = 1, message="eventRequestDTO.err.minLengthUserId")
    private Long userId;

}
