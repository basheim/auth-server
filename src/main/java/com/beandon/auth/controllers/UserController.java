package com.beandon.auth.controllers;

import com.beandon.auth.pojos.users.PartialUser;
import com.beandon.auth.pojos.users.RefreshRequest;
import com.beandon.auth.pojos.users.Tokens;
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
    @CrossOrigin()
    @PostMapping("/authenticate")
    public Tokens authenticate(Authentication authentication) {
        return Tokens.builder()
                .token(authService.getToken(authentication))
                .refresh(authService.getRefresh(authentication))
                .name(authentication.getName())
                .build();
    }

    @PostMapping("/refresh")
    public Tokens refresh(@RequestBody RefreshRequest refreshRequest) {
        String token = authService.refreshToken(refreshRequest.getRefresh(), refreshRequest.getName());
        return Tokens.builder()
                .token(token)
                .refresh(refreshRequest.getRefresh())
                .name(refreshRequest.getName())
                .build();
    }

    @GetMapping("/check")
    public void check() {}
}
