package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
    public Optional<Personne> findById(Long id);
}