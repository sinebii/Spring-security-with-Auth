package com.jwt.jwtsecurity.service.implimentation;

import com.jwt.jwtsecurity.entity.BaseUser;
import com.jwt.jwtsecurity.exception.UserException;
import com.jwt.jwtsecurity.payload.request.CreateUserReq;
import com.jwt.jwtsecurity.payload.response.CreateUserRes;
import com.jwt.jwtsecurity.repository.BaseUserRepository;
import com.jwt.jwtsecurity.service.BaseUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    private BaseUserRepository baseUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper mapper;
    @Override
    public CreateUserRes createNewUser(CreateUserReq createUserReq) {

        if(baseUserRepository.findByEmail(createUserReq.getEmail())!=null) throw new UserException("User with "+createUserReq.getEmail()+ " already exist");
        BaseUser user = BaseUser.builder()
                .firstName(createUserReq.getFirstName())
                .lastName(createUserReq.getLastName())
                .email(createUserReq.getEmail())
                .password(passwordEncoder.encode(createUserReq.getPassword()))
                .role("USER")
                .build();
        BaseUser saveUser =  baseUserRepository.save(user);
        return mapper.map(saveUser, CreateUserRes.class);
    }
}
