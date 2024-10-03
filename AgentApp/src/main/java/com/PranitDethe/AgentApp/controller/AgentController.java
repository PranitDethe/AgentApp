package com.PranitDethe.AgentApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PranitDethe.AgentApp.model.Agent;
import com.PranitDethe.AgentApp.service.AgentService;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin(origins = "*")
public class AgentController {
    @Autowired
    private AgentService agentService;

    @PostMapping("/purchase/{userId}")
    public Agent purchaseAgent(@PathVariable Long userId) {
        return agentService.purchaseAgent(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Agent> getAgentsByUser(@PathVariable Long userId) {
        return agentService.getAgentsByUserId(userId);
    }

    @PutMapping("/configure/{agentId}")
    public Agent configureAgent(@PathVariable Long agentId, @RequestParam String brands) {
        return agentService.updateAgentConfiguration(agentId, brands);
    }

    @PostMapping("/status")
    public Agent updateAgentStatus(@RequestBody AgentStatusRequest request) {
        return agentService.updateAgentStatus(request.getAgentId(), request.getStatus());
    }
}


class AgentStatusRequest {
    private String agentId;
    private String status;
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

   
}