package com.chael.Atlas_Way_sb.mappers;

import com.chael.Atlas_Way_sb.dtos.AttractionDto;
import com.chael.Atlas_Way_sb.entities.Attraction;
import com.chael.Atlas_Way_sb.entities.User;
import com.chael.Atlas_Way_sb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AttractionDtoToAttraction {
    private Attraction attraction;
    @Autowired
    private UserRepository userRepository;

    public Attraction toAttraction(AttractionDto attractionDto) {
        if (attractionDto.ownerId() == null) {
            throw new IllegalArgumentException("Owner ID must not be null");
        }

        User owner = userRepository.findById(attractionDto.ownerId())
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
