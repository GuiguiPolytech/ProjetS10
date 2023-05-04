package com.example.repository;

import java.util.List;
import com.example.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
    List<Personne> findByProfil(String profil);
}