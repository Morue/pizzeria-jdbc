package com.pizzeria.model;

/*
 * Credit Mélanie Diligent
 */

import java.util.ArrayList;

import com.pizzeria.classe.Pizza;
import com.pizzeria.exception.SavePizzaException;
import com.pizzeria.exception.StockageException;
import com.pizzeria.exception.UpdatePizzaException;

public interface IPizzaDao {
		void createPizza(Pizza pizza) throws SavePizzaException;
		void updatePizza(Pizza pizza) throws UpdatePizzaException;
		void deletePizza(int id) throws StockageException;
		ArrayList<Pizza> getAllPizza();
		Pizza findOnePizza(int id);

}