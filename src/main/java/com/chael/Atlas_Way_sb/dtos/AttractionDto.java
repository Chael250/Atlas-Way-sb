package com.chael.Atlas_Way_sb.dtos;

import lombok.Data;

public record AttractionDto(
        String name,
        String description,
        String location,
        Double price,
        Long ownerId
) {
}
