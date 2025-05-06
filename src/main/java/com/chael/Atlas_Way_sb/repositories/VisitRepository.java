package com.chael.Atlas_Way_sb.repositories;

import com.chael.Atlas_Way_sb.entities.Visits;
import com.chael.Atlas_Way_sb.entities.enums.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visits, Long> {
    @Query("SELECT v FROM Visits v where v.user = :id")
    public List<Visits> findAllByOwner(Long id);

    @Query("DELETE FROM Visits v WHERE v.user = :id")
    public void deleteByOwner(Long id);

    @Query("UPDATE Visits v SET v.visitDate = :date, v.visited = :visited, v.rating = :rating WHERE v.user = :user")
    public void updateByOwner(Date date, boolean visited, Rating rating, Long user);
}
