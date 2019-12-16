package com.example.authservice.model;

import com.example.model.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class SimpleUser {

    @JsonProperty("username")
    private final String username;

    @JsonIgnore
    private final String password;

    @JsonProperty("roles")
    private Set<Role> roles;

    @JsonCreator
    public SimpleUser(@JsonProperty(value = "username", required = true) final String username,
                      @JsonProperty(value = "password", required = true) final String password,
                      @JsonProperty(value = "roles", required = true) final Set<Role> roles){
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
