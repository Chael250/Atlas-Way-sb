package com.chael.Atlas_Way_sb.repositories;

import com.chael.Atlas_Way_sb.entities.User;
import com.chael.Atlas_Way_sb.entities.Visits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT v.user FROM Visits v where v.attraction = :attraction")
    public Page<Visits> findAllUserByAttraction(Long attraction, Pageable pageable);
}
