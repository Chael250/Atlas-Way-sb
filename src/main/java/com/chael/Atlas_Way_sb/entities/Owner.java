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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OWNER_TB")
public class Owner extends UserEntity{
    @OneToMany(
            mappedBy = "owner"
    )
    @JsonManagedReference
    private List<Attraction> attractions;
}
