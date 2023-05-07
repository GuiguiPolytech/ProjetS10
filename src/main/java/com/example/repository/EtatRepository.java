package com.example.repository;

import com.example.model.Etat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtatRepository extends JpaRepository<Etat, Long> {
    Etat findByLibelle(String libelle);
}
