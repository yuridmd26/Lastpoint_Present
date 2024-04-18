package br.com.present.attendances.consumer.model;

import lombok.Getter;

@Getter
public class EventResponseDTO {

    private Long id;
    private String code;
    private String name;
    private String startDateTime;
    private String endDateTime;
    private String description;

}