package com.chael.Atlas_Way_sb.services;

import com.chael.Atlas_Way_sb.dtos.UserDto;
import com.chael.Atlas_Way_sb.entities.User;
import com.chael.Atlas_Way_sb.mappers.UserDtoToUser;
import com.chael.Atlas_Way_sb.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoToUser dtoToUser;

    public UserService(UserRepository userRepository, UserDtoToUser dtoToUser) {
        this.userRepository = userRepository;
        this.dtoToUser = dtoToUser;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);

    }

    public User save(UserDto userdto) {
        User user = dtoToUser.user(userdto);
        return userRepository.save(user);

    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void update(Long id, UserDto userdto) {
        User oldUser = userRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("User not found with id " + id));
        oldUser.setUsername(userdto.username());
        oldUser.setPassword(userdto.password());
        oldUser.setEmail(userdto.email());
        userRepository.save(oldUser);
    }
}
