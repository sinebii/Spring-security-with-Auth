package com.jwt.jwtsecurity.eventlistener;

import com.jwt.jwtsecurity.entity.BaseUser;
import com.jwt.jwtsecurity.event.RegistrationCompleteEvent;
import com.jwt.jwtsecurity.service.BaseUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j
public class RegistrationCompleteListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    BaseUserService baseUserService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Todo:: Create Verification Token for the user
        BaseUser baseUser = event.getBaseUser();
        String token = UUID.randomUUID().toString();
        baseUserService.saveVerificationTokenForUser(token,baseUser);
        String url = event.getApplicationUrl()+"/users/verifyRegistration/"+token;
        //Todo:: Send verification email
        log.info("Click here to verify your account {}",url);
    }
}
