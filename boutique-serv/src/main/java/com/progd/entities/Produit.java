package com.progd.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="produit")
@Getter @Setter
public class Produit implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	private BigDecimal  prix;
	
	private Long id_categorie;

	public Long getId() {
		return id;
	}

	
	
	public Produit(String nom, BigDecimal prix, Long id_categorie) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.id_categorie = id_categorie;
	}
	public Produit() {
		
	}
}
