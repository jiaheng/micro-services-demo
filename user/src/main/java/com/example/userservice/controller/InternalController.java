package com.example.userservice.controller;

import com.example.model.User;
import com.example.exception.NotFoundException;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal")
public class InternalController {

    private final UserService userService;

    @Autowired
    public InternalController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login/{username}")
    public ResponseEntity<User> getUserLoginDetails(@PathVariable final String username) {
        return userService.findUserLoginDetails(username)
                .map(ResponseEntity::ok)
                .orElseThrow(NotFoundException::new);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody final User user) {
        return userService.addUser(user);
    }

}
