package com.chael.Atlas_Way_sb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ATTRACTION_TB")
public class Attraction extends BaseEntity {
    private String name;

    private String description;

    private String location;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
