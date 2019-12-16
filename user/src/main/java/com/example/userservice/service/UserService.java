package com.example.userservice.service;

import com.example.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserDetails(String username);

    User addUser(User user);

    Optional<User> findUserLoginDetails(String username);

}
