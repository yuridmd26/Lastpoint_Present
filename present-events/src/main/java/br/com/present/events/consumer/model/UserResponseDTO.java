package br.com.present.events.consumer.model;

import lombok.Getter;

@Getter
public class UserResponseDTO {

    private Long id;
    private String code;
    private String name;
    private String login;
    private String type;

}