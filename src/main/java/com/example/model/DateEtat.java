package com.example.model;


import java.time.LocalDateTime;
import java.time.LocalTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class DateEtat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateEtat;

    private LocalTime heureEtat;

    @ManyToOne
    private Etat etat;

    public DateEtat(LocalDateTime dateEtat,LocalTime heureEtat , Etat etat) {
        this.dateEtat = dateEtat;
        this.heureEtat = heureEtat;
        this.etat = etat;
    }
}
