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
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
    
    @PostMapping("")
    public FraisDeplacement createFraisDeplacement(@RequestBody FraisDeplacement fraisDeplacement) {
        return fraisDeplacementRepository.save(fraisDeplacement);
    }
    
    @GetMapping("")
    public List<FraisDeplacement> getAllFraisDeplacements() {
        return fraisDeplacementRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public FraisDeplacement getFraisDeplacementById(@PathVariable Long id) throws NotFoundException {
        return fraisDeplacementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }
    
    @PutMapping("/{id}")
    public FraisDeplacement updateFraisDeplacement(@PathVariable Long id, @RequestBody FraisDeplacement updatedFraisDeplacement) throws NotFoundException {
        FraisDeplacement fraisDeplacement = fraisDeplacementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        
        fraisDeplacement.setMotif(updatedFraisDeplacement.getMotif());
        fraisDeplacement.setMoyenTransport(updatedFraisDeplacement.getMoyenTransport());
        fraisDeplacement.setDateDepart(updatedFraisDeplacement.getDateDepart());
        fraisDeplacement.setPersonne(updatedFraisDeplacement.getPersonne());
        fraisDeplacement.setEtats(updatedFraisDeplacement.getEtats());
        
        return fraisDeplacementRepository.save(fraisDeplacement);
    }
    
    @DeleteMapping("/{id}")
    public void deleteFraisDeplacement(@PathVariable Long id) {
        fraisDeplacementRepository.deleteById(id);
    }
    
    @PostMapping("/{id}/etat")
    public FraisDeplacement addEtatToFraisDeplacement(@PathVariable Long id, @RequestParam Long etatId) throws NotFoundException {
        FraisDeplacement fraisDeplacement = fraisDeplacementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        Etat etat = etatRepository.findById(etatId)
                .orElseThrow(() -> new NotFoundException());
        
        fraisDeplacement.getEtats().add(etat);
        return fraisDeplacementRepository.save(fraisDeplacement);
    }
    
    @DeleteMapping("/{id}/etat")
    public FraisDeplacement removeEtatFromFraisDeplacement(@PathVariable Long id, @RequestParam Long etatId) throws NotFoundException {
        FraisDeplacement fraisDeplacement = fraisDeplacementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        Etat etat = etatRepository.findById(etatId)
                .orElseThrow(() -> new NotFoundException());
        
        fraisDeplacement.getEtats().remove(etat);
        return fraisDeplacementRepository.save(fraisDeplacement);
    }
}
