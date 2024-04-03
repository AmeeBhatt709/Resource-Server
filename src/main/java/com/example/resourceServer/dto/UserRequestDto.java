package com.example.resourceServer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDto {

    private Long id;
    private String email;
    private String password;
    private Boolean read;
    private Boolean write;
    private Boolean remove;


    public UserRequestDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
