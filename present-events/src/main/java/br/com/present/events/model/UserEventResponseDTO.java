package br.com.present.events.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "User Event Response")
public class UserEventResponseDTO {
	
	private Long id;
    private Long userId;
    private Long eventId;

}