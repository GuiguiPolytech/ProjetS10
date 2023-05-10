package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.DateEtat;

public interface DateEtatRepository extends JpaRepository<DateEtat, Long> {
}
