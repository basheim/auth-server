package com.beandon.auth.controllers;

import com.beandon.auth.pojo.users.PartialUser;
import com.beandon.auth.services.AuthService;
import com.beandon.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthService authService;

    @PostMapping("/users")
    public void createUser(@RequestBody PartialUser user) {
        userService.createUser(user);
    }

    @GetMapping("/users/{username}")
    public boolean userExists(@PathVariable String username) {
        return userService.usernameExists(username);
    }

    @DeleteMapping("/users/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @PostMapping("/authenticate")
    public String authenticate(Authentication authentication) {
        return authService.getToken(authentication);
    }
}
