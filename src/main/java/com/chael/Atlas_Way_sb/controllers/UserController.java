package com.chael.Atlas_Way_sb.controllers;

import com.chael.Atlas_Way_sb.entities.User;
import com.chael.Atlas_Way_sb.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atlas-way/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(
            @PathVariable("id") Long id
    ) {
        return userService.findById(id);
    }

    @PostMapping()
    public User addUser(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public User deleteUserById(
            @PathVariable Long id
    ){
        User user = userService.findById(id);
        userService.deleteById(id);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(
            @RequestBody User user
            ,@PathVariable Long id
    ){
        User oldUser = userService.findById(id);
        userService.update(id, user);
        return oldUser;
    }
}
