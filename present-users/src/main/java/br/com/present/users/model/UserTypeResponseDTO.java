package br.com.present.users.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(name = "User Type Response")
public class UserTypeResponseDTO {

    private String type;

}