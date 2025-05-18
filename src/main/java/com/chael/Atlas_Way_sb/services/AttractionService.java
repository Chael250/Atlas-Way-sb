package com.chael.Atlas_Way_sb.services;

import com.chael.Atlas_Way_sb.dtos.AttractionDto;
import com.chael.Atlas_Way_sb.entities.Attraction;
import com.chael.Atlas_Way_sb.mappers.AttractionDtoToAttraction;
import com.chael.Atlas_Way_sb.repositories.AttractionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {
    private final AttractionRepository attractionRepository;
    private final AttractionDtoToAttraction attractionDtoToAttraction;

    public AttractionService(AttractionRepository attractionRepository, AttractionDtoToAttraction attractionDtoToAttraction) {
        this.attractionRepository = attractionRepository;
        this.attractionDtoToAttraction = attractionDtoToAttraction;
    }
    public List<Attraction> findAll() {
        return attractionRepository.findAll();
    }

    public Attraction findById(Long id) {
        return attractionRepository.findById(id).orElse(null);
    }

    public Attraction save(AttractionDto attractionDto) {
        Attraction attraction = attractionDtoToAttraction.toAttraction(attractionDto);
        return attractionRepository.save(attraction);
    }

    public void deleteById(Long id) {
        attractionRepository.deleteById(id);
    }

    public boolean updateProduct(Long id, AttractionDto attractionDto) {
        if (attractionDto == null) {
            throw new IllegalArgumentException("Attraction cannot be null");
        }

        Optional<Attraction> existingAttractionOpt = attractionRepository.findById(id);
        if (existingAttractionOpt.isEmpty()) {
            return false; // or throw new ResourceNotFoundException("Attraction not found with id: " + id);
        }

        Attraction existingAttraction = existingAttractionOpt.get();

        // Update fields manually (or use a mapper if available)
        attractionDtoToAttraction.toAttraction(attractionDto);
        // Add any other fields you need to update

        attractionRepository.save(existingAttraction);
        return true;
    }
    public Page<Attraction> findPageAttraction(int page, int size, String column, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(column).descending() : Sort.by(column).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return attractionRepository.findAll(pageable);
    }
}
