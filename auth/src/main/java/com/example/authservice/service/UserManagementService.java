package com.example.authservice.service;

import com.example.authservice.model.SimpleUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserManagementService extends UserDetailsService {

    SimpleUser registerUser(SimpleUser user);

}
