package com.chael.Atlas_Way_sb.services;

import com.chael.Atlas_Way_sb.entities.Attraction;
import com.chael.Atlas_Way_sb.entities.User;
import com.chael.Atlas_Way_sb.entities.Visits;
import com.chael.Atlas_Way_sb.repositories.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final AttractionService attractionService;
    private final UserService userService;

    public VisitService(VisitRepository visitRepository, AttractionService attractionService, UserService userService) {
        this.visitRepository = visitRepository;
        this.attractionService = attractionService;
        this.userService = userService;
    }

    public List<Visits> findAllByOwner(Long ownerId) {
        return visitRepository.findAllByOwner(ownerId);
    }

    public List<Visits> findAllByTourist(Long touristId) {
        return visitRepository.findAllByOwner(touristId);
    }

    public Visits findByIdAndOwner(Long id) {
        return visitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Visit not found with id " + id));
    }

    public Visits createByOwner(Visits visit, Long user, Long attraction) {
        Attraction attract = attractionService.findById(attraction);
        User use = userService.findById(user);
        visit.setAttraction(attract);
        visit.setUser(use);
        return visitRepository.save(visit);
    }

    public void deleteByOwner(Long id){
        visitRepository.deleteById(id);
    }

    public void updateByOwner(Visits visit, Long user) {
        visitRepository.updateByOwner(visit.getVisitDate(), visit.isVisited(), visit.getRating(), user);
    }
}
