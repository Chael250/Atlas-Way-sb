package com.chael.Atlas_Way_sb.services;

import com.chael.Atlas_Way_sb.dtos.UserDto;
import com.chael.Atlas_Way_sb.entities.Tourist;
import com.chael.Atlas_Way_sb.mappers.UserDtoToUser;
import com.chael.Atlas_Way_sb.repositories.TouristRepository;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TouristService {
    private final TouristRepository touristRepository;
    private final UserDtoToUser userDtoToUser;

    public TouristService(TouristRepository touristRepository, UserDtoToUser userDtoToUser) {
        this.touristRepository = touristRepository;
        this.userDtoToUser = userDtoToUser;
    }

    public List<UserDto> findAll() {
        return touristRepository.findAll()
                .stream()
                .map(userDtoToUser::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        Tourist tourist = touristRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tourist not found"));
        return userDtoToUser.toUserDto(tourist);
    }

    public UserDto save(UserDto userdto) {
        Tourist tourist = userDtoToUser.toTourist(userdto);
        return userDtoToUser.toUserDto(touristRepository.save(tourist));
    }

    public UserDto update(Long id, UserDto userdto){
        Optional<Tourist> existingTourist = touristRepository.findById(id);
        if (existingTourist.isPresent()) {
            Tourist tourist = userDtoToUser.toTourist(userdto);
            return userDtoToUser.toUserDto(touristRepository.save(tourist));
        } else {
            throw new IllegalArgumentException("Tourist not found");
        }
    }

    public void delete(Long id) {
        touristRepository.deleteById(id);
    }

    public Page<UserDto> findAllPaginated(int page, int size, String order, String column, Long id){
        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by(column).descending() :
                Sort.by(column).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return touristRepository.findAll(pageable)
                .map(userDtoToUser::toUserDto);
    }
}
