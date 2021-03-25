package com.progd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progd.entities.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{

}
