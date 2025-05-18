package com.chael.Atlas_Way_sb.mappers;

import com.chael.Atlas_Way_sb.dtos.UserDto;
import com.chael.Atlas_Way_sb.entities.Owner;
import com.chael.Atlas_Way_sb.entities.Tourist;
import com.chael.Atlas_Way_sb.entities.embedded.Names;
import com.chael.Atlas_Way_sb.entities.enums.UserType;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUser {
    public Tourist toTourist(UserDto userdto) {
        Names names = new Names();
        names.setFirstName(userdto.firstname());
        names.setLastName(userdto.lastname());

        Tourist user = new Tourist();
        user.setNames(names);
        user.setEmail(userdto.email());
        user.setPassword(userdto.password());
        user.setUsername(userdto.username());
        user.setUserType(UserType.TOURIST);
        return user;
    }

    public Owner toOwner(UserDto userdto) {
        Names names = new Names();
        names.setFirstName(userdto.firstname());
        names.setLastName(userdto.lastname());

        Owner user = new Owner();
        user.setNames(names);
        user.setEmail(userdto.email());
        user.setPassword(userdto.password());
        user.setUsername(userdto.username());
        user.setUserType(UserType.OWNER);
        return user;
    }

    public UserDto toUserDto(Tourist tourist) {
        return new UserDto(
                tourist.getUsername(),
                tourist.getPassword(),
                tourist.getEmail(),
                tourist.getNames().getFirstName(),
                tourist.getNames().getLastName()
        );
    }

    public UserDto toUserDto(Owner owner) {
        return new UserDto(
                owner.getUsername(),
                owner.getPassword(),
                owner.getEmail(),
                owner.getNames().getFirstName(),
                owner.getNames().getLastName()
        );
    }
}
