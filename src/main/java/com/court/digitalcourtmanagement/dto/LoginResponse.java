package com.court.digitalcourtmanagement.dto;

public class LoginResponse {
    private String token;
    private String role;
    private String username;
    private Long domainEntityId;

    public LoginResponse(String token, String role, String username, Long domainEntityId) {
        this.token = token;
        this.role = role;
        this.username = username;
        this.domainEntityId = domainEntityId;
    }

    public String getToken() { return token; }
    public String getRole() { return role; }
    public String getUsername() { return username; }
    public Long getDomainEntityId() { return domainEntityId; }
}