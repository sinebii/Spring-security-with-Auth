package com.jwt.jwtsecurity.controller;

import com.jwt.jwtsecurity.payload.request.CreateUserReq;
import com.jwt.jwtsecurity.payload.response.CreateUserRes;
import com.jwt.jwtsecurity.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private BaseUserService baseUserService;
    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public ResponseEntity<CreateUserRes> createNewUser(@RequestBody CreateUserReq createUserReq){
        return new ResponseEntity<>(baseUserService.createNewUser(createUserReq), HttpStatus.CREATED);
        publisher.publishEvent();
    }
}
