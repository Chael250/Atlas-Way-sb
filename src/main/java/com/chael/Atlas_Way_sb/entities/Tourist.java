package com.chael.Atlas_Way_sb.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TOURIST_TB")
public class Tourist extends UserEntity{
    @OneToMany(
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Visits> visits;
}
