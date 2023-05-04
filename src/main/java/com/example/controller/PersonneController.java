package com.example.controller;

import java.util.List;
import com.example.model.Demandeur;
import com.example.model.Manager;
import com.example.model.Personne;
import com.example.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personnes")
public class PersonneController {
    @Autowired
    private PersonneRepository personneRepository;

    @GetMapping
    public List<Personne> getAll() {
        return personneRepository.findAll();
    }

    @PostMapping("/demandeurs")
    public Demandeur createDemandeur(@RequestBody Demandeur demandeur) {
        return personneRepository.save(demandeur);
    }

    @PostMapping("/managers")
    public Manager createManager(@RequestBody Manager manager) {
        return personneRepository.save(manager);
    }
}