package com.example.userservice.service.impl;

import com.example.model.User;
import com.example.exception.ConflictException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserDetails(final String username) {
        return userRepository.findUserDetails(username);
    }

    @Override
    public User addUser(final User user) {
        // perform validation
        checkNotNull(user.getEncodedPassword());
        checkNotNull(user.getUsername());

        try {
            return userRepository.save(user);
        } catch (final DuplicateKeyException e) {
            throw new ConflictException("User already Exists", e);
        }
    }

    @Override
    public Optional<User> findUserLoginDetails(final String username) {
        return userRepository.findUserLoginDetails(username);
    }
}
