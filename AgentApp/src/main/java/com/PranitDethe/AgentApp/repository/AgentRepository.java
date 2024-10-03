package com.PranitDethe.AgentApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PranitDethe.AgentApp.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    List<Agent> findByUserId(Long userId);

    Agent findByAgentId(String agentId);
}
