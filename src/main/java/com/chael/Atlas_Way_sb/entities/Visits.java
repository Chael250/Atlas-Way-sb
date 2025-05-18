package com.chael.Atlas_Way_sb.entities;

import com.chael.Atlas_Way_sb.entities.enums.Rating;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VISITS_TB")
public class Visits extends BaseEntity {
    private Date visitDate;

    private boolean visited;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private Tourist user;

    @ManyToOne
    @JoinColumn(name = "attraction_id")
    @JsonManagedReference
    private Attraction attraction;
}
