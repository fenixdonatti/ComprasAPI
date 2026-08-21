package com.estudo.api.dto.user;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public UserCreateDTO() {
    }

    public UserCreateDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
