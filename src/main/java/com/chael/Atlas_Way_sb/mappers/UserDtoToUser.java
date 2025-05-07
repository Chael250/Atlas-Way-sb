package com.chael.Atlas_Way_sb.mappers;

import com.chael.Atlas_Way_sb.dtos.UserDto;
import com.chael.Atlas_Way_sb.entities.User;
import com.chael.Atlas_Way_sb.entities.embedded.Names;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUser {
    public User user(UserDto userdto) {
        Names names = new Names();
        names.setFirstName(userdto.firstname());
        names.setLastName(userdto.lastname());

        User user = new User();
        user.setNames(names);
        user.setEmail(userdto.email());
        user.setPassword(userdto.password());
        user.setUsername(userdto.username());
        user.setUserType(userdto.type());
        return user;
    }
}
