package com.sudhir.webfluxexample.controller;

import com.sudhir.webfluxexample.dto.UserResponse;
import com.sudhir.webfluxexample.model.User;
import com.sudhir.webfluxexample.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody User user) {
        log.info(user.toString());
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser( @PathVariable("id") Long id , @RequestBody User user) {
        log.info(String.valueOf(id));
        log.info(user.toString());

        return userService.updateUser(id,user);

    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }
}
