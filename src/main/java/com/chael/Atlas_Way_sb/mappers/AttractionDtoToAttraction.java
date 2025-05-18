package com.chael.Atlas_Way_sb.mappers;

import com.chael.Atlas_Way_sb.dtos.AttractionDto;
import com.chael.Atlas_Way_sb.entities.Attraction;
import com.chael.Atlas_Way_sb.entities.Owner;
import com.chael.Atlas_Way_sb.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttractionDtoToAttraction {
    private Attraction attraction;
    @Autowired
    private OwnerRepository ownerRepository;

    public Attraction toAttraction(AttractionDto attractionDto) {
        if (attractionDto.ownerId() == null) {
            throw new IllegalArgumentException("Owner ID must not be null");
        }

        Owner owner = ownerRepository.findById(attractionDto.ownerId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + attractionDto.ownerId()));

        Attraction attraction = new Attraction();
        attraction.setName(attractionDto.name());
        attraction.setDescription(attractionDto.description());
        attraction.setLocation(attractionDto.location());
        attraction.setPrice(attractionDto.price());
        attraction.setOwner(owner);

        return attraction;
    }
}
