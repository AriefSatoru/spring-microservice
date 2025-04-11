package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    
    Optional<UserInfo> findByName(String name);
}
