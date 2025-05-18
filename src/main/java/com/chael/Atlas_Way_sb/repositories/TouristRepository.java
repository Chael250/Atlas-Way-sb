package com.chael.Atlas_Way_sb.repositories;

import com.chael.Atlas_Way_sb.entities.Tourist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TouristRepository extends JpaRepository<Tourist, Long> {
    @Query("SELECT v.user FROM Visits v where v.attraction = :attraction")
    public Page<Tourist> findAllUserByAttraction(Long attraction, Pageable pageable);
}
