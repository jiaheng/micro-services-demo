package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @JsonIgnore
    private String id;

    @Indexed(unique = true, name = "username_index", direction = IndexDirection.DESCENDING)
    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty("encoded_password")
    private String encodedPassword;

    @JsonProperty("roles")
    private Set<Role> roles;

    @JsonCreator
    public User(
            @JsonProperty(value = "username", required = true) final String username,
            @JsonProperty(value = "first_name") final String firstName,
            @JsonProperty(value = "last_name") final String lastName,
            @JsonProperty(value = "encoded_password") final String encodedPassword,
            @JsonProperty(value = "roles") final Set<Role> roles) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.encodedPassword = encodedPassword;
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setEncodedPassword(final String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
}
