package br.com.present.events.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(name = "User Event Request")
public class UserEventRequestDTO {

    @NotNull(message = "userEventRequestDTO.err.notNullUserId")
    @Min(value = 1, message="userEventRequestDTO.err.minLengthUserId")
    private Long userId;

    @NotNull(message = "userEventRequestDTO.err.notNullEventId")
    @Min(value = 1, message="userEventRequestDTO.err.minLengthEventId")
    private Long eventId;

}