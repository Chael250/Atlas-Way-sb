package com.chael.Atlas_Way_sb.services;

import com.chael.Atlas_Way_sb.entities.Attraction;
import com.chael.Atlas_Way_sb.entities.Tourist;
import com.chael.Atlas_Way_sb.entities.Visits;
import com.chael.Atlas_Way_sb.repositories.AttractionRepository;
import com.chael.Atlas_Way_sb.repositories.TouristRepository;
import com.chael.Atlas_Way_sb.repositories.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final AttractionService attractionService;
    private final TouristRepository touristRepository;
    private final AttractionRepository attractionRepository;

    public VisitService(VisitRepository visitRepository, AttractionService attractionService, TouristRepository touristRepository, TouristService touristService, AttractionRepository attractionRepository) {
        this.visitRepository = visitRepository;
        this.attractionService = attractionService;
        this.touristRepository = touristRepository;
        this.attractionRepository = attractionRepository;
    }

    public List<Visits> findAllByOwner(Long ownerId) {
        return visitRepository.findAllByOwner(ownerId);
    }

    public List<Visits> findAllByAttraction(Long attractionId) {
        return visitRepository.findAllByAttraction(attractionId);
    }

    public List<Visits> findAllByTourist(Long touristId){
        return visitRepository.findAllByUser(touristId);
    }

    public Visits findByIdAndOwner(Long id) {
        return visitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Visit not found with id " + id));
    }

    public Visits createByOwner(Visits visit) {
        Optional<Attraction> attract = attractionRepository.findById(visit.getAttraction().getId());
        Optional<Tourist> use = touristRepository.findById(visit.getUser().getId());
        if (use.isPresent() && attract.isPresent()) {
            return visitRepository.save(visit);
        } else {
            throw new EntityNotFoundException("Tourist not found with id");
        }
    }

    public void deleteByOwner(Long id){
        visitRepository.deleteById(id);
    }

    public void updateByOwner(Visits visit, Long user) {
        visitRepository.updateByOwner(visit.getVisitDate(), visit.isVisited(), visit.getRating(), user);
    }
}
