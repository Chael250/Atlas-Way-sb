package com.chael.Atlas_Way_sb.controllers;

import com.chael.Atlas_Way_sb.dtos.UserDto;
import com.chael.Atlas_Way_sb.entities.Owner;
import com.chael.Atlas_Way_sb.services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atlas-way/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getOwners() {
        return ownerService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto getOwnerById(
            @PathVariable("id") Long id
    ) {
        return ownerService.findById(id);
    }

    @PostMapping()
    public UserDto addOwner(@RequestBody UserDto userdto){
        return ownerService.save(userdto);
    }

    @PutMapping("updateOwner/{id}")
    public UserDto updateOwner(@PathVariable("id") Long id, @RequestBody UserDto userdto){
        return ownerService.update(id, userdto);
    }

    @DeleteMapping("deleteOwner/{id}")
    public UserDto deleteOwnerById(
            @PathVariable Long id
    ){
        UserDto user = ownerService.findById(id);
        ownerService.delete(id);
        return user;
    }

}
