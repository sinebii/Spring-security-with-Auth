package com.jwt.jwtsecurity.controller;

import com.jwt.jwtsecurity.event.RegistrationCompleteEvent;
import com.jwt.jwtsecurity.payload.request.CreateUserReq;
import com.jwt.jwtsecurity.payload.response.CreateUserRes;
import com.jwt.jwtsecurity.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private BaseUserService baseUserService;


    @PostMapping("/register")
    public ResponseEntity<CreateUserRes> createNewUser(@RequestBody CreateUserReq createUserReq, final HttpServletRequest request){
        return new ResponseEntity<>(baseUserService.createNewUser(createUserReq,request), HttpStatus.CREATED);
    }
    @GetMapping("/verifyRegistration/{token}")
    public ResponseEntity<String> verifyToken(@PathVariable(name = "token") String token){
        return new ResponseEntity<>(baseUserService.validateToken(token),HttpStatus.OK);
    }
}
