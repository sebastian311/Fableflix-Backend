package com.backend.fableflix.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.backend.fableflix.Enums.UserRoles;

@Entity
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    private String email;

    private String password;

    private UserRoles role;
// Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
