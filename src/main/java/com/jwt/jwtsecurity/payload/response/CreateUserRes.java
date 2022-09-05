package com.jwt.jwtsecurity.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRes {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String rePassword;
}
