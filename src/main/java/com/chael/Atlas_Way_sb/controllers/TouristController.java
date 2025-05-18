package com.chael.Atlas_Way_sb.controllers;

import com.chael.Atlas_Way_sb.dtos.UserDto;
import com.chael.Atlas_Way_sb.entities.Tourist;
import com.chael.Atlas_Way_sb.services.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atlas-way/tourists")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return touristService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(
            @PathVariable("id") Long id
    ) {
        return touristService.findById(id);
    }

    @PostMapping()
    public UserDto addUser(@RequestBody UserDto userdto){
        return touristService.save(userdto);
    }

    @PutMapping("updateTourist/{id}")
    public UserDto updateTourist(
            @PathVariable Long id,
            @RequestBody UserDto userdto
    ){
        return touristService.update(id, userdto);
    }

    @DeleteMapping("deleteTourist/{id}")
    public UserDto deleteUserById(
            @PathVariable Long id
    ){
        UserDto user = touristService.findById(id);
        touristService.delete(id);
        return user;
    }
}
