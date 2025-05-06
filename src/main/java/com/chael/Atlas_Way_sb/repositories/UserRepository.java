package com.chael.Atlas_Way_sb.repositories;

import com.chael.Atlas_Way_sb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
