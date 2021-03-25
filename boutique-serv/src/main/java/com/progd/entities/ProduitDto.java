package com.progd.entities;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProduitDto {
	private Long id;
	
	private String nom;
	
	private BigDecimal  prix;
	
	private Categorie categorie;
	
}
