package com.chael.Atlas_Way_sb.controllers;

import com.chael.Atlas_Way_sb.dtos.UserDto;
import com.chael.Atlas_Way_sb.entities.User;
import com.chael.Atlas_Way_sb.services.UserService;
import com.chael.Atlas_Way_sb.util.Constants;
import org.springframework.data.domain.Page;
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
    public User addUser(@RequestBody UserDto userdto){
        return userService.save(userdto);
    }

    @DeleteMapping("/{id}")
    public User deleteUserById(
            @PathVariable Long id
    ){
        User user = userService.findById(id);
        userService.deleteById(id);
        return user;
    }

    @PutMapping("/update/{id}")
    public User updateUser(
            @RequestBody UserDto userdto
            ,@PathVariable Long id
    ){
        User oldUser = userService.findById(id);
        userService.update(id, userdto);
        return oldUser;
    }

    @GetMapping("/pageTourist/{id}")
    public Page<User> getTouristPage(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "column", defaultValue = "username") String column,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            @PathVariable Long id
    ){
        return userService.findPageTourist(page, size, column, direction, id);
    }
}
