package com.pizzeria.classe;

/*
 * Credit Mélanie Diligent
 */

public class Pizza {
	
	private String code = null;
	private String designation = null;
	private Double price = null;
	private int id;
	private Categorie categorie;
	
	/**
	 * 
	 * @param code de la pizza (les trois premieres lettre en Maj)
	 * @param designation Nom de la pizza
	 * @param price prix de la pizza
	 */
	public Pizza(int id,String code, String designation, Double price, Categorie categorie) {
		this.id= id;
		this.code = code;
		this.designation = designation;
		this.price = price;
		this.categorie = categorie;
	}
	public Pizza(String code, String designation, Double price, Categorie cat) {
		
		this.code = code;
		this.designation = designation;
		this.price = price;
		this.categorie = cat;
	}
	
	
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return retourne le code de la pizza
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 
	 * @param code modifi le code de la pizza
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 
	 * @return retourne la designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * 
	 * @param designation modifi la designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * 
	 * @return retourne le prix de la pizza
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price modification du prix
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie cat) {
		this.categorie = cat;
	}
	/**
	 * 
	 * @return methode pour afficher le menu de l'application
	 */
	
	
	
	public String toString() {

		return "id=>"+this.id +" -> "+this.code +" -> "+ this.designation +"("+ this.price+" €) c'est une pizza de type =>"+this.categorie ;
	}

}
