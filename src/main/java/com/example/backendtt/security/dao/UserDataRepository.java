package com.example.backendtt.security.dao;

import com.example.backendtt.security.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsernameEquals(String username);
}