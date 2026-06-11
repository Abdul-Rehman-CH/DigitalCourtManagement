package com.court.digitalcourtmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_users")
@Data
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private Long domainEntityId;

    public AppUser(String username, String password, Role role, Long domainEntityId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.domainEntityId = domainEntityId;
    }

    public void setUsername(String us) {
        this.username = us;
    }

    public String getUsername() {
        return this.username;
    }

    public void setDomainEntityId(Long id) {
        this.domainEntityId = id;
    }

    public Long getDomainEntityId() {
        return this.domainEntityId;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getPassword() {
        return this.password;
    }
}
