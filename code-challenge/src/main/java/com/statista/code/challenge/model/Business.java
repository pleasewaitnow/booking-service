package com.statista.code.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Random;

public class Business {

    @JsonProperty("booking_id")
    private LocalDateTime timestamp;
    private String departmentName;
    private String agent;

    public Business(LocalDateTime timestamp, String departmentName) {
        this.timestamp = timestamp;
        this.departmentName = departmentName;
        this.agent = generateRandomAgentName();
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAgent() {
        return agent;
    }

    private String generateRandomAgentName() {
        String[] agentNames = { "Max", "Amy", "Mike", "Marina", "Alex", "Olivia", "William" };
        int randomIndex = new Random().nextInt(agentNames.length);
        return agentNames[randomIndex];
    }
}