package com.example.authservice.controller;

import com.example.authservice.model.SimpleUser;
import com.example.authservice.service.UserManagementService;
import com.example.model.Role;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserManagementService userManagementService;

    @Autowired
    public AuthController(final UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PutMapping("/register")
    public SimpleUser registerUser(@RequestBody final SimpleUser user) {
        user.setRoles(Sets.newHashSet(Role.USER));
        return userManagementService.registerUser(user);
    }

}
