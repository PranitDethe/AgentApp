package com.PranitDethe.AgentApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PranitDethe.AgentApp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}