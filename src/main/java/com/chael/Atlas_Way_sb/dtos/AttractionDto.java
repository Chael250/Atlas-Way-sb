package com.chael.Atlas_Way_sb.dtos;

public record AttractionDto(
        String name,
        String description,
        String location,
        Double price,
        Long ownerId
) {
}
