package com.chael.Atlas_Way_sb.controllers;

import com.chael.Atlas_Way_sb.entities.Attraction;
import com.chael.Atlas_Way_sb.repositories.AttractionRepository;
import com.chael.Atlas_Way_sb.services.AttractionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atlas-way/attractions")
public class AttractionController {
    private final AttractionRepository attractionRepository;
    private final AttractionService attractionService;

    public AttractionController(AttractionRepository attractionRepository, AttractionService attractionService) {
        this.attractionRepository = attractionRepository;
        this.attractionService = attractionService;
    }

    @GetMapping()
    public List<Attraction> getAllAttractions() {
        return attractionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Attraction getAttractionById(@PathVariable Long id) {
        return attractionRepository.findById(id).orElse(null);
    }

    @PostMapping()
    public Attraction createAttraction(@RequestBody Attraction attraction) {
        return attractionRepository.save(attraction);
    }

    @DeleteMapping("/{id}")
    public Attraction deleteAttraction(@PathVariable Long id) {
        Attraction attraction = attractionRepository.findById(id).orElse(null);
        attractionRepository.deleteById(id);
        return attraction;
    }

    @PutMapping("/{id}")
    public Attraction updateAttraction(@PathVariable Long id, @RequestBody Attraction attraction) {
        Attraction oldAttraction = attractionRepository.findById(id).orElse(null);
        attractionService.update(id, attraction);
        return oldAttraction;
    }
}
