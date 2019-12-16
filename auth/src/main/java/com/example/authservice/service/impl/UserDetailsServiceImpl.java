package com.example.authservice.service.impl;

import com.example.authservice.model.SimpleUser;
import com.example.authservice.service.UserManagementService;
import com.example.exception.ConflictException;
import com.example.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserDetailsServiceImpl implements UserManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private static final String USER_SERVICE_INTERNAL_URL = "http://user-service/internal";
    private static final String ADD_USER_ENDPOINT = USER_SERVICE_INTERNAL_URL + "/";
    private static final String LOGIN_DETAILS_ENDPOINT = USER_SERVICE_INTERNAL_URL + "/login/{username}";

    private final PasswordEncoder encoder;
    private final RestTemplate restTemplate;

    @Autowired
    public UserDetailsServiceImpl(final PasswordEncoder encoder, final RestTemplate restTemplate) {
        this.encoder = encoder;
        this.restTemplate = restTemplate;
    }

    @Override
    public SimpleUser registerUser(final SimpleUser appUser) {
        checkNotNull(appUser.getUsername());
        checkNotNull(appUser.getPassword());

        final com.example.model.User user = new com.example.model.User(
                appUser.getUsername(),
                null,
                null,
                encoder.encode(appUser.getPassword()),
                appUser.getRoles());


        try {
            final ResponseEntity<com.example.model.User> response = restTemplate.exchange(ADD_USER_ENDPOINT, HttpMethod.PUT, new HttpEntity<>(user), com.example.model.User.class);
            final com.example.model.User createdUser = response.getBody();
            return new SimpleUser(createdUser.getUsername(), null, createdUser.getRoles());
        } catch (final HttpStatusCodeException e) {
            switch (e.getStatusCode()) {
                case CONFLICT:
                    throw new ConflictException("User already exists", e);
                default:
                    throw new RuntimeException("dont know what happen", e);
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            final com.example.model.User user = restTemplate.getForObject(LOGIN_DETAILS_ENDPOINT, com.example.model.User.class, username);
            final String commaSeperatedAuthorities = user.getRoles()
                    .stream()
                    .map(Role::getHeaderValue)
                    .collect(Collectors.joining(","));
            // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
            // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeperatedAuthorities);
            // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
            // And used by auth manager to verify and check user authentication.
            LOGGER.info("Returning user: {}", username);
            return new User(user.getUsername(), user.getEncodedPassword(), grantedAuthorities);
        } catch (final HttpStatusCodeException e) {
            if (e.getStatusCode().equals(NOT_FOUND)) {
                LOGGER.warn("Username: {} not found", username);
                throw new UsernameNotFoundException("Username: " + username + " not found", e);
            } else {
                throw new RuntimeException("Dont know what happen", e);
            }
        }
    }

}