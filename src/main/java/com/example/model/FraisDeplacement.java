package com.example.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class FraisDeplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motif;
    private String moyenTransport;
    private LocalDate dateDepart;

    @ManyToOne
    private Personne personne;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Etat> etats = new HashSet<>();
}