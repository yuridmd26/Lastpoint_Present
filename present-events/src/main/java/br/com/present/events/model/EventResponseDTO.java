package br.com.present.events.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Event Response")
public class EventResponseDTO {
	
    private Long id;
    private String code;
    private String name;
    private String description;
    private OffsetDateTime startDateTime;
    private OffsetDateTime endDateTime;
    private Long userId;
    
}
