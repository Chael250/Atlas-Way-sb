package com.chael.Atlas_Way_sb.services;

import com.chael.Atlas_Way_sb.dtos.UserDto;
import com.chael.Atlas_Way_sb.entities.User;
import com.chael.Atlas_Way_sb.entities.Visits;
import com.chael.Atlas_Way_sb.mappers.UserDtoToUser;
import com.chael.Atlas_Way_sb.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoToUser dtoToUser;
    private final VisitService visitService;

    public UserService(UserRepository userRepository, UserDtoToUser dtoToUser, VisitService visitService) {
        this.userRepository = userRepository;
        this.dtoToUser = dtoToUser;
        this.visitService = visitService;
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

    public Page<User> findPageTourist(int page, int size, String column, String direction, Long id) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(column).descending() :
                Sort.by(column).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return userRepository.findAllUserByAttraction(id, pageable);
    }
}
