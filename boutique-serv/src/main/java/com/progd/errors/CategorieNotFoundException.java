package com.progd.errors;

public class CategorieNotFoundException extends RuntimeException{

	public CategorieNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategorieNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
    public CategorieNotFoundException(Long id) {
        super("Categorie id not found : " + id);
    }
}
