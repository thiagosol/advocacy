package com.advocacy.advocacysystem.entrypoint.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginRequestDTO {

    private final String username;
    private final String password;

}
