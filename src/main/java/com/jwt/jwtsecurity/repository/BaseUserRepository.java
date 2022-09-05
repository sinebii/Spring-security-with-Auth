package com.jwt.jwtsecurity.repository;

import com.jwt.jwtsecurity.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {
    BaseUser findByEmail(String email);
}
