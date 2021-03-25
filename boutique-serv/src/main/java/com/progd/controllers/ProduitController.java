package com.progd.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.progd.entities.Categorie;
import com.progd.entities.Produit;
import com.progd.entities.ProduitDto;
import com.progd.errors.ProduitNotFoundException;
import com.progd.repositories.CategorieRepository;
import com.progd.repositories.ProduitRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
public class ProduitController {

    @Autowired
    private ProduitRepository repository;
    
    @Autowired
    private CategorieRepository cRepository;
    
    private static Logger logger = LogManager.getLogger(ProduitController.class);

    // Find
    @GetMapping("/produits")
    List<ProduitDto> findAll() {
    	logger.info("produits : findAll()");
    	List<Produit> listP = repository.findAll();
    	List<ProduitDto> listPDto = new ArrayList<>();
    	listPDto = listP.stream()
    			.map(p->{
    		ProduitDto pDto = new ProduitDto();
    		pDto.setId(p.getId());
    		pDto.setNom(p.getNom());
    		pDto.setPrix(p.getPrix());
    		System.out.println("id="+p.getId()+" nom="+p.getNom()+" categorie="+p.getId_categorie());
    		Categorie c = null;
    		if(null != p.getId_categorie()) {
    			c = cRepository.findById(p.getId_categorie()).get();
    		}
    		pDto.setCategorie(c);
    		return pDto;
    	})
    	.collect(Collectors.toList() );

        return listPDto;
    }

    // Save
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/produit")
    Produit newProduit(@RequestBody Produit newProduit) {
    	logger.info("produits : save("+newProduit.getNom()+")");
    	newProduit = repository.save(newProduit);
        return newProduit;
    }

    // Find
    @GetMapping("/produit/{id}")
    Produit findOne(@PathVariable Long id) {
    	logger.info("produits : findById("+id+")");
        return repository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException(id));
    }

    // Save or update
    @PutMapping("/produit/{id}")
    Produit saveOrUpdate(@RequestBody Produit newProduit, @PathVariable Long id) {
    	logger.info("produits : update("+newProduit.getNom()+")");
        return repository.findById(id)
                .map(x -> {
                    x.setNom(newProduit.getNom());
                    x.setPrix(newProduit.getPrix());
                    x.setId_categorie(newProduit.getId_categorie());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newProduit.setId(id);
                    return repository.save(newProduit);
                });
    }

    // update prix only
    @PatchMapping("/produit/{id}")
    Produit patch(@RequestBody Map<String, Double> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    Double prix = update.get("prix");
                    if (!StringUtils.isEmpty(prix)) {
                        x.setPrix(new BigDecimal(prix));
                        return repository.save(x);
                    } else {
                    	return null;
                        //throw new ProduitUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new ProduitNotFoundException(id);
                });

    }

    @DeleteMapping("/produit/{id}")
    void deleteProduit(@PathVariable Long id) {
    	System.out.println("delete :"+id);
        repository.deleteById(id);
    }
}
