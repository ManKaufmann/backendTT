package com.example.backendtt.security.dao;

import com.example.backendtt.security.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
}