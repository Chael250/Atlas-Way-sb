package com.chael.Atlas_Way_sb.entities;

import com.chael.Atlas_Way_sb.entities.embedded.Names;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.chael.Atlas_Way_sb.entities.enums.UserType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_TB")
public class User extends BaseEntity {
    private String username;

    private String password;

    private String email;

    private Names names;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(
            mappedBy = "user"
    )
    private List<Visits> visits;

    @OneToMany(
            mappedBy = "owner"
    )
    private List<Attraction> attractions;
}
