package com.pizzeria.appli;

/*
 * Credit Mélanie Diligent
 */


import java.util.Scanner;
import com.pizzeria.model.CategorieDao;
import com.pizzeria.model.PizzaDao;
import com.pizzeria.exception.DeletePizzaException;
import com.pizzeria.exception.SavePizzaException;
import com.pizzeria.exception.UpdatePizzaException;
import com.pizzeria.classe.Categorie;
import com.pizzeria.classe.Pizza;

public class PizzeriaAdminConsoleApp{
    public static void main( String[] args ){
       PizzaDao maPizza = new PizzaDao();
       CategorieDao maCategorie = new CategorieDao();
       
     		boolean end = false;
     		int id_categorie;
     		Categorie categorie;
     		Pizza pizza;
     		Scanner scan = new Scanner(System.in);
     		
     		while(!end) {
     			System.out.println("----------------------");
     			System.out.println("\"***** Pizzeria Administration *****\r\n" + 
     					"1. Lister les pizzas\r\n" + 
     					"2. Ajouter une nouvelle pizza\r\n" + 
     					"3. Mettre à jour une pizza\r\n" + 
     					"4. Supprimer une pizza\r\n"+
     					"5. reset ma liste de pizza\r\n" + 
     					"99. Sortir");
     			int value = Integer.parseInt(scan.nextLine());
     			switch (value) {

     			case 1:
     				
     				for (Pizza pizz : maPizza.getAllPizza()) {
						System.out.println(pizz.toString());
					}
     				break;

     			case 2:

     				System.out.println("----------------------");
     				System.out.println("Ajout d’une nouvelle pizza");		
     				System.out.println("Veuillez saisir le code");
     				String code = scan.nextLine();
     				System.out.println("Veuillez saisir le nom (sans espace) :");
     				String name = scan.nextLine();
     				System.out.println("Veuillez le prix:");
     				Double price = Double.valueOf(scan.nextLine());
     				System.out.println("Veuillez saisir la categorie \n\r"
     						+ "° tapez 1 pour Fromage \r\n"
     						+ "° 2 pour Viande \r\n"
     						+ "° 3 pour Poisson \r\n"
     						+ "° 4 pour Autre");
     				
     				id_categorie = Integer.parseInt(scan.nextLine());
     				categorie  = maCategorie.getOneCategorie(id_categorie);
     				pizza = new Pizza(code, name, price, categorie);
     			
					try {
						maPizza.createPizza(pizza);
					} catch (SavePizzaException e) {
						System.out.println(e.getMessage());
					}
     			
     				break;

     			case 3:
     				
     				System.out.println("----------------------");
     				System.out.println("Mise à jour d’une pizza");
     				System.out.println("Veuillez saisir l'id de la pizza à modifier");
     				int id = Integer.parseInt(scan.nextLine()) ;
     				System.out.println("Veuillez saisir le nouveau code");
     				code = scan.nextLine();
     				System.out.println("Veuillez saisir le nouveau nom :");
     				name = scan.nextLine();
     				System.out.println("Veuillez le nouveau prix:");
     				price = Double.valueOf(scan.nextLine());
     				System.out.println("Veuillez saisir la categorie \n\r"
     						+ "° tapez 1 pour Fromage \r\n"
     						+ "° 2 pour Viande \r\n"
     						+ "° 3 pour Poisson \r\n"
     						+ "° 4 pour Autre \r\n\"");
     				id_categorie = Integer.parseInt(scan.nextLine());
     				categorie  = maCategorie.getOneCategorie(id_categorie);
     				pizza = new Pizza(id,code, name,price,categorie );
     				try {
     					maPizza.updatePizza(pizza);
     				} catch (UpdatePizzaException e) {    				
     					System.err.println(e.getMessage()+" update");
     				}
     				break;

     			case 4:
     				System.out.println("----------------------");
     				System.out.println("Suppression d’une pizza");
     			
     				System.out.println("Veuillez saisir le id de la pizza à supprimer");
     				id = Integer.parseInt(scan.nextLine());
					try {
						maPizza.deletePizza(id);
					} catch (DeletePizzaException e1) {
						System.out.println(e1.getMessage());
					}
     				break;	

     			case 5:
     				try {
     					maPizza.resetAllPizza();
     					} catch (Exception e) {
     					System.err.println(e);
     				}
     				break;	
     			case 99:
     			default:
     				System.out.println("----------------------");
     				System.out.println("Au revoir");
     				scan.close();
     				end =true;
     				break;
     			}
     			
     		}
     	}
}