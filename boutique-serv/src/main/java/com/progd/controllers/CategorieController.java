package com.progd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.progd.entities.Categorie;
import com.progd.errors.CategorieNotFoundException;
import com.progd.repositories.CategorieRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
public class CategorieController {
	   @Autowired
	    private CategorieRepository repository;

	   // Find All
	    @GetMapping("/categories")
	    List<Categorie> findAll() {
	        return repository.findAll();
	    }
	
	    // Add
	    @ResponseStatus(HttpStatus.CREATED)
	    @PostMapping("/categorie")
	    Categorie newCategorie(@RequestBody Categorie newCategorie) {
	    	newCategorie = repository.save(newCategorie);
	    	System.out.println(newCategorie.getNom()+" créée avec id "+newCategorie.getId());
	        return newCategorie;
	    }

	    // Find one
	    @GetMapping("/categorie/{id}")
	    Categorie findOne(@PathVariable Long id) {
	        return repository.findById(id)
	                .orElseThrow(() -> new CategorieNotFoundException(id));
	    }
}
