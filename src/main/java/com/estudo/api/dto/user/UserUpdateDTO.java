package com.estudo.api.dto.user;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class UserUpdateDTO {
    @NotNull
    private UUID uuid;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(UUID uuid, String name, String email) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }
}
