package br.com.present.classes.consumer.model;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class EventResponseDTO {

    private Long id;
    private String code;
    private String name;
    private OffsetDateTime startDateTime;
    private OffsetDateTime endDateTime;
    private String description;

}