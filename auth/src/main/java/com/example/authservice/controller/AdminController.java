package com.example.authservice.controller;

import com.example.authservice.model.SimpleUser;
import com.example.authservice.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/admin")
public class AdminController {

    private final UserManagementService userManagementService;

    @Autowired
    public AdminController(final UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PutMapping("/register")
    public SimpleUser registerUser(@RequestBody final SimpleUser user) {
        return userManagementService.registerUser(user);
    }

}
