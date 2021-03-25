package com.progd.errors;

public class ProduitNotFoundException extends RuntimeException{
    public ProduitNotFoundException(Long id) {
        super("Produit id not found : " + id);
    }
}
