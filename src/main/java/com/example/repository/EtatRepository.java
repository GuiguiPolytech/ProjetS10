package com.example.repository;

import com.example.model.Etat;
import com.example.model.FraisDeplacement;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EtatRepository extends JpaRepository<Etat, Long> {
    Etat findByLibelle(String libelle);

    Set<Etat> findByFraisDeplacements(FraisDeplacement fraisDeplacement);
}
