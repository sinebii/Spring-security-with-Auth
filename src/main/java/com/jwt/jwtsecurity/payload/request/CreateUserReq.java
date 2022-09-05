package com.jwt.jwtsecurity.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserReq {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String rePassword;
}
