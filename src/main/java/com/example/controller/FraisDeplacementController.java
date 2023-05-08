package com.example.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.example.model.Etat;
import com.example.model.FraisDeplacement;
import com.example.model.Personne;
import com.example.repository.EtatRepository;
import com.example.repository.FraisDeplacementRepository;
import com.example.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/frais-deplacement")
public class FraisDeplacementController {
    @Autowired
    private FraisDeplacementRepository fraisDeplacementRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private EtatRepository etatRepository;

    // @PostMapping
    // public FraisDeplacement create(@RequestBody FraisDeplacement fraisDeplacement) {
    //     Personne personne = personneRepository.findById(fraisDeplacement.getPersonne().getId())
    //             .orElseThrow(() -> new RuntimeException("Personne not found"));
    //     fraisDeplacement.setPersonne(personne);
    //     return fraisDeplacementRepository.save(fraisDeplacement);
    // }

    @PostMapping
    public FraisDeplacement create(@RequestBody FraisDeplacement fraisDeplacement, @RequestParam Long etatId) {
        Personne personne = personneRepository.findById(fraisDeplacement.getPersonne().getId())
                .orElseThrow(() -> new RuntimeException("Personne not found"));
        fraisDeplacement.setPersonne(personne);
        Etat etat = etatRepository.findById(etatId)
                .orElseThrow(() -> new RuntimeException("Etat not found"));
        etat.getFraisDeplacements().size(); // Charge explicitement la collection de fraisDeplacements de l'objet Etat
        fraisDeplacement.getEtats().add(etat);
        return fraisDeplacementRepository.save(fraisDeplacement);
    }


    @GetMapping
    public List<FraisDeplacement> getAll() {
        return fraisDeplacementRepository.findAll();
    }

    @GetMapping("/{id}")
    public FraisDeplacement getById(@PathVariable Long id) {
        return fraisDeplacementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Frais de déplacement not found"));
    }

    @PutMapping("/{id}/etat")
    public FraisDeplacement updateEtats(@PathVariable Long id, @RequestBody List<Long> etatIds) {
        FraisDeplacement fraisDeplacement = fraisDeplacementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Frais de déplacement not found"));
        Set<Etat> etats = new HashSet<>();
        for (Long etatId : etatIds) {
            Etat etat = etatRepository.findById(etatId)
                    .orElseThrow(() -> new RuntimeException("Etat not found"));
            etats.add(etat);
        }
        fraisDeplacement.setEtats(etats);
        return fraisDeplacementRepository.save(fraisDeplacement);
    }
}

