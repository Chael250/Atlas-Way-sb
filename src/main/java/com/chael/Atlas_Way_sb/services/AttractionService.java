package com.chael.Atlas_Way_sb.services;

import com.chael.Atlas_Way_sb.entities.Attraction;
import com.chael.Atlas_Way_sb.entities.User;
import com.chael.Atlas_Way_sb.repositories.AttractionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionService {
    private final AttractionRepository attractionRepository;

    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }
    public List<Attraction> findAll() {
        return attractionRepository.findAll();
    }

    public Attraction findById(Long id) {
        return attractionRepository.findById(id).orElse(null);
    }

    public Attraction save(Attraction attraction) {
        return attractionRepository.save(attraction);
    }

    public void deleteById(Long id) {
        attractionRepository.deleteById(id);
    }

    public void update(Long id, Attraction attraction) {
        Attraction oldAttraction = attractionRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("User not found with id " + id));
        oldAttraction.setName(attraction.getName());
        oldAttraction.setDescription(attraction.getDescription());
        oldAttraction.setLocation(attraction.getLocation());
        oldAttraction.setPrice(attraction.getPrice());
        attractionRepository.save(oldAttraction);
    }
}
