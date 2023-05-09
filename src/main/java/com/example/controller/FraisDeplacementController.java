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
        // Création d'un nouvel état
        Etat nouvelEtat = new Etat("En attente");
        etatRepository.save(nouvelEtat);

        // Ajout de l'état créé à la liste d'états du frais de déplacement
        Set<Etat> etats = fraisDeplacement.getEtats();
        etats.add(nouvelEtat);
        fraisDeplacement.setEtats(etats);

        // Enregistrement du frais de déplacement mis à jour
        return fraisDeplacementRepository.save(fraisDeplacement);
    }

    
    @GetMapping("")
    public List<FraisDeplacement> getAllFraisDeplacements() {
        List<FraisDeplacement> fraisDeplacements = fraisDeplacementRepository.findAll();

        for (FraisDeplacement fraisDeplacement : fraisDeplacements) {
            Set<Etat> etats = etatRepository.findByFraisDeplacements(fraisDeplacement);
            fraisDeplacement.setEtats(etats);
        }

        return fraisDeplacements;
    }

    
    @GetMapping("/{id}")
    public FraisDeplacement getFraisDeplacementById(@PathVariable Long id) throws NotFoundException {
        FraisDeplacement fraisDeplacement = fraisDeplacementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        Set<Etat> etats = etatRepository.findByFraisDeplacements(fraisDeplacement);
        fraisDeplacement.setEtats(etats);

        return fraisDeplacement;
    }

    @PutMapping("/{id}")
    public FraisDeplacement updateFraisDeplacementById(@PathVariable Long id, @RequestBody FraisDeplacement fraisDeplacement) throws NotFoundException {
        // Recherche du frais de déplacement à mettre à jour
        FraisDeplacement fraisDeplacementToUpdate = fraisDeplacementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        // Mise à jour des champs
        fraisDeplacementToUpdate.setDateDepart(fraisDeplacement.getDateDepart());
        fraisDeplacementToUpdate.setMoyenTransport(fraisDeplacement.getMoyenTransport());
        fraisDeplacementToUpdate.setMotif(fraisDeplacement.getMotif());

        // Enregistrement du frais de déplacement mis à jour
        fraisDeplacementRepository.save(fraisDeplacementToUpdate);

        // Récupération du frais de déplacement mis à jour avec la liste d'états associés
        FraisDeplacement fraisDeplacementUpdated = fraisDeplacementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        Set<Etat> etats = etatRepository.findByFraisDeplacements(fraisDeplacementUpdated);
        fraisDeplacementUpdated.setEtats(etats);

        return fraisDeplacementUpdated;
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
