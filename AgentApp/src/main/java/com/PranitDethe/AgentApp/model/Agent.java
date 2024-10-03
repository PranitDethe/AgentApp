package com.PranitDethe.AgentApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agentId; 
    private String status;
    private String supportedBrands;

    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Agent(Long id, String agentId, String status, String supportedBrands, User user) {
		super();
		this.id = id;
		this.agentId = agentId;
		this.status = status;
		this.supportedBrands = supportedBrands;
		this.user = user;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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


	public String getSupportedBrands() {
		return supportedBrands;
	}


	public void setSupportedBrands(String supportedBrands) {
		this.supportedBrands = supportedBrands;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Agent [id=" + id + ", agentId=" + agentId + ", status=" + status + ", supportedBrands="
				+ supportedBrands + ", user=" + user + "]";
	}
    
    

    
}