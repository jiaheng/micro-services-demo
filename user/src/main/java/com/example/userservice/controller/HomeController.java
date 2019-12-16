package com.example.userservice.controller;

import com.example.model.User;
import com.example.exception.NotFoundException;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable final String username) {
        return userService.findUserDetails(username)
                .map(ResponseEntity::ok)
                .orElseThrow(NotFoundException::new);
    }

}
