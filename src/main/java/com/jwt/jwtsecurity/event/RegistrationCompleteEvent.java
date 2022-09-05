package com.jwt.jwtsecurity.event;

import com.jwt.jwtsecurity.entity.BaseUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private BaseUser baseUser;
    private String applicationUrl;

    public RegistrationCompleteEvent(BaseUser baseUser, String applicationUrl) {
        super(baseUser);
        this.baseUser = baseUser;
        this.applicationUrl = applicationUrl;
    }

}
