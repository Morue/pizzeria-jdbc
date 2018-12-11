package com.pizzeria.classe;

/*
 * Credit Mélanie Diligent
 */

public class Categorie {
	int id;
	String name;
	
	public Categorie(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return  name ;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
