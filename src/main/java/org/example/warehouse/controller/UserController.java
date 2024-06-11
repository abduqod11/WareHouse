package org.example.warehouse.controller;

import org.example.warehouse.dto.UserDto;
import org.example.warehouse.model.Result;
import org.example.warehouse.model.User;
import org.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")

public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public User getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result createUser(@RequestBody UserDto userdto) {
        return userService.addUser(userdto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result updateUser(@PathVariable Integer id, UserDto userDto) {
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result deleteUserById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }
}