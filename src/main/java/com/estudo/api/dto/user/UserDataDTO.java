package com.estudo.api.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataDTO {
    
    private String name;
    private String email;

    public UserDataDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
