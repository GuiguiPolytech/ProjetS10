package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.DateEtat;
import com.example.repository.DateEtatRepository;

@RestController
@RequestMapping("/date-etat")
@CrossOrigin(origins = "*")
public class DateEtatController {

    @Autowired
    private DateEtatRepository dateEtatRepository;

    @GetMapping
    public List<DateEtat> getAll() {
        return dateEtatRepository.findAll();
    }
    
}
