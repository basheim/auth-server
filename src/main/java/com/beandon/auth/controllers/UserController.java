package com.beandon.auth.controllers;

import com.beandon.auth.pojo.users.Auth;
import com.beandon.auth.pojo.users.PartialUser;
import com.beandon.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public void createUser(@RequestBody PartialUser user) {
        userService.createUser(user);
    }

    @DeleteMapping("/users/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @PostMapping("/authenticate")
    public void authenticate(@RequestBody Auth auth) {

    }
}
