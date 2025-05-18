package com.chael.Atlas_Way_sb.services;

import com.chael.Atlas_Way_sb.dtos.UserDto;
import com.chael.Atlas_Way_sb.entities.Owner;
import com.chael.Atlas_Way_sb.entities.Tourist;
import com.chael.Atlas_Way_sb.mappers.UserDtoToUser;
import com.chael.Atlas_Way_sb.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final UserDtoToUser userDtoToUser;

    public OwnerService(OwnerRepository ownerRepository, UserDtoToUser userDtoToUser) {
        this.ownerRepository = ownerRepository;
        this.userDtoToUser = userDtoToUser;
    }

    public List<UserDto> findAll() {
        return ownerRepository.findAll()
                .stream()
                .map(userDtoToUser::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        Owner owner = ownerRepository.findById(id).orElse(null);
        if(owner == null) {
            throw new IllegalArgumentException("Owner not found");
        }
        return userDtoToUser.toUserDto(owner);
    }

    public UserDto save(UserDto userdto) {
        Owner owner = userDtoToUser.toOwner(userdto);
        return userDtoToUser.toUserDto(ownerRepository.save(owner));
    }

    public UserDto update(Long id, UserDto userdto){
        Optional<Owner> existingOwner = ownerRepository.findById(id);
        if (existingOwner.isPresent()) {
            Owner owner = userDtoToUser.toOwner(userdto);
            owner.setId(id);
            return userDtoToUser.toUserDto(ownerRepository.save(owner));
        } else {
            throw new IllegalArgumentException("Tourist not found");
        }
    }

    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }
}
