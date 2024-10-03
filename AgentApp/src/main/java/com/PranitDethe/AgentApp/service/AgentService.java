package com.PranitDethe.AgentApp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PranitDethe.AgentApp.model.Agent;
import com.PranitDethe.AgentApp.model.User;
import com.PranitDethe.AgentApp.repository.AgentRepository;
import com.PranitDethe.AgentApp.repository.UserRepository;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;

    // Purchase a new agent for the user
    public Agent purchaseAgent(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User user = userOptional.get();
        Agent agent = new Agent();
        agent.setAgentId(UUID.randomUUID().toString()); // Generate a unique ID for the agent
        agent.setStatus("Idle");
        agent.setUser(user); // Associate agent with user
        return agentRepository.save(agent); // Save agent to DB
    }

    // Get all agents for a user
    public List<Agent> getAgentsByUserId(Long userId) {
        return agentRepository.findByUserId(userId);
    }

    // Update agent configuration
    public Agent updateAgentConfiguration(Long agentId, String brands) {
        Optional<Agent> agentOptional = agentRepository.findById(agentId);
        if (!agentOptional.isPresent()) {
            throw new RuntimeException("Agent not found with ID: " + agentId);
        }

        Agent agent = agentOptional.get();
        agent.setSupportedBrands(brands);
        return agentRepository.save(agent); // Save updated agent
    }

    // Update agent status (e.g., when agent sends periodic heartbeat)
    public Agent updateAgentStatus(String agentId, String status) {
        Agent agent = agentRepository.findByAgentId(agentId);
        if (agent == null) {
            throw new RuntimeException("Agent not found with ID: " + agentId);
        }

        agent.setStatus(status); // Update agent's status
        return agentRepository.save(agent); // Save updated agent
    }
}