package com.jwt.jwtsecurity.service;

import com.jwt.jwtsecurity.entity.BaseUser;
import com.jwt.jwtsecurity.payload.request.CreateUserReq;
import com.jwt.jwtsecurity.payload.response.CreateUserRes;

import javax.servlet.http.HttpServletRequest;

public interface BaseUserService {
    CreateUserRes createNewUser(CreateUserReq createUserReq, HttpServletRequest request);
    void saveVerificationTokenForUser(String token, BaseUser baseUser);

    String validateToken(String token);
}
