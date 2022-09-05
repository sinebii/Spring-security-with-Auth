package com.jwt.jwtsecurity.service.implimentation;

import com.jwt.jwtsecurity.entity.BaseUser;
import com.jwt.jwtsecurity.entity.VerificationToken;
import com.jwt.jwtsecurity.event.RegistrationCompleteEvent;
import com.jwt.jwtsecurity.exception.UserException;
import com.jwt.jwtsecurity.payload.request.CreateUserReq;
import com.jwt.jwtsecurity.payload.response.CreateUserRes;
import com.jwt.jwtsecurity.repository.BaseUserRepository;
import com.jwt.jwtsecurity.repository.VerificationTokenRepository;
import com.jwt.jwtsecurity.service.BaseUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    private BaseUserRepository baseUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;



    @Override
    public CreateUserRes createNewUser(CreateUserReq createUserReq, HttpServletRequest request) {

        if(!createUserReq.getPassword().equals(createUserReq.getRePassword())) throw new UserException("Passwords do not match");
        if(baseUserRepository.findByEmail(createUserReq.getEmail())!=null) throw new UserException("User with "+createUserReq.getEmail()+ " already exist");
        BaseUser user = BaseUser.builder()
                .firstName(createUserReq.getFirstName())
                .lastName(createUserReq.getLastName())
                .email(createUserReq.getEmail())
                .password(passwordEncoder.encode(createUserReq.getPassword()))
                .role("USER")
                .build();
        BaseUser saveUser =  baseUserRepository.save(user);
        publisher.publishEvent(new RegistrationCompleteEvent(saveUser,applicationUrl(request)));
        return mapper.map(saveUser, CreateUserRes.class);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();
    }

    @Override
    public void saveVerificationTokenForUser(String token, BaseUser baseUser) {
        VerificationToken verificationToken = new VerificationToken(baseUser,token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken ==null)throw new UserException("Invalid token");
        BaseUser baseUser = verificationToken.getBaseUser();
        Calendar calendar = Calendar.getInstance();
        if(verificationToken.getExpirationTime().getTime()-calendar.getTime().getTime() <=0){
            verificationTokenRepository.delete(verificationToken);
            throw new UserException("Token is expired");
        }
        baseUser.setEnabled(true);
        baseUserRepository.save(baseUser);
        return "Account has been verified";
    }
}
