package com.example.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Etat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;

    @ManyToMany(mappedBy = "etats")
    private Set<FraisDeplacement> fraisDeplacements = new HashSet<>();

    public Etat(String libelle) {
        this.libelle = libelle;
    }
}
