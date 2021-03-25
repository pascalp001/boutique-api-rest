package com.progd;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.progd.entities.Categorie;
import com.progd.entities.Produit;
import com.progd.repositories.CategorieRepository;
import com.progd.repositories.ProduitRepository;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		Produit p1 = new Produit();
		SpringApplication.run(StartApplication.class, args);
	}
    // init bean to insert 3 products into h2 database.
    @Bean
    CommandLineRunner initDatabase(ProduitRepository pRepository, CategorieRepository cRepository) {
        return args -> {
        	cRepository.save(new Categorie("legume"));
        	Categorie c1 = cRepository.findById(1L).get();
        	Categorie c2 = cRepository.save(new Categorie("fruit"));
        	System.out.println(c1.getNom()+" : "+c1.getId());
        	System.out.println(c2.getNom()+" : "+c2.getId());
        	pRepository.save(new Produit("tomate",  new BigDecimal("2.50"), 1L));
        	pRepository.save(new Produit("carotte",  new BigDecimal("1.20"), 1L));
        	pRepository.save(new Produit("laitue",  new BigDecimal("1.00"), 1L));
        };
    }
}
