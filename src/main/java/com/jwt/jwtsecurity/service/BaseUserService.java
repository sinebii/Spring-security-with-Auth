package com.jwt.jwtsecurity.service;

import com.jwt.jwtsecurity.payload.request.CreateUserReq;
import com.jwt.jwtsecurity.payload.response.CreateUserRes;

public interface BaseUserService {
    CreateUserRes createNewUser(CreateUserReq createUserReq);
}
