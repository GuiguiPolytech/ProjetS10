package com.example.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "profil")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nom;
    private String prenom;

    public String getTypeProfil() {
        DiscriminatorValue discriminatorValue = getClass().getAnnotation(DiscriminatorValue.class);
        return discriminatorValue == null ? null : discriminatorValue.value();
    }
    
}