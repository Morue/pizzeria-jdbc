package com.pizzeria.model;

/*
 * Credit Mélanie Diligent
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pizzeria.jdbc.Connect;
import com.pizzeria.exception.SavePizzaException;
import com.pizzeria.exception.StockageException;
import com.pizzeria.exception.UpdatePizzaException;
import com.pizzeria.exception.DeletePizzaException;

import com.pizzeria.classe.Categorie;
import com.pizzeria.classe.Pizza;

public class PizzaDao implements IPizzaDao{
	final int  CODE_LONG = 4;
	final int PRICE_MAX = 20;
	final int PRICE_MIN = 5;
	
	public void createPizza(Pizza pizza) throws SavePizzaException {
		Connection conn = null;
		PreparedStatement ps = null;
		int result ;
		try {
			conn = Connect.getConnection();
			if(checkMaPizza(pizza)) {
				ps = conn.prepareStatement("INSERT INTO pizza (code, designation,prix,id_categorie) values (?,?,?,?)");
				ps.setString(1,pizza.getCode());
				ps.setString(2,pizza.getDesignation());
				ps.setDouble(3,pizza.getPrice());
				ps.setInt(4,pizza.getCategorie().getId());
				result = ps.executeUpdate();
				if(result==0) System.out.println("l'insert la pas abouti");
				}
			} catch (Exception e) {
			throw new SavePizzaException(e.getMessage()+"---------------insertPizza*********");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage()+"******conn.close()*********");
			}
		}
	}

	public void updatePizza(Pizza pizza) throws UpdatePizzaException{
		Connection conn = null;
		PreparedStatement ps = null;
		int result ;
		try {
			conn = Connect.getConnection();
			if(checkMaPizza(pizza) && findOnePizza(pizza.getId())!=null) {	
				ps = conn.prepareStatement("UPDATE pizza SET code =?, designation=?, prix=?, id_categorie=? WHERE id = ?");
				ps.setString(1,pizza.getCode());
				ps.setString(2,pizza.getDesignation());
				ps.setDouble(3,pizza.getPrice());
				ps.setInt(4,pizza.getCategorie().getId());
				ps.setInt(5,pizza.getId());
				result = ps.executeUpdate();
				if(result==0) {
					System.out.println("l'update la pas abouti");
				}
			}
		} catch (Exception e) {
			throw new UpdatePizzaException(e.getMessage());
		}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage()+"******conn.close()---------------updatePizza*********");
				}
			}
		}
		

	public void deletePizza(int id) throws DeletePizzaException {
		if(T(id)!=null) {
			Connection conn = null;
			PreparedStatement ps = null;
			int result ;
			try {
				conn = Connect.getConnection();
				ps = conn.prepareStatement("DELETE FROM pizza WHERE id = ?");
				ps.setInt(1,id);
				result = ps.executeUpdate();
				if(result==0) System.out.println("le delete la pas abouti");
			} catch (Exception e) {
				throw new DeletePizzaException( e.getMessage()+"******deletePizza*********");
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new DeletePizzaException(e.getMessage()+"******conn.close()---------------deletePizza*********");
				}
			}
		}else {
			throw new DeletePizzaException("la pizza a supprimer n'existe pas ");
		}
	}

	public ArrayList<Pizza> getAllPizza() {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		ArrayList<Pizza> ToutesMesPizzas = new ArrayList<Pizza>();
		try{
			conn = Connect.getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery("SELECT pizza.id, code, designation, prix , id_categorie, categorie.nom FROM pizza JOIN categorie ON pizza.id_categorie = categorie.id");
			while (rs.next()){
				ToutesMesPizzas.add(new Pizza(rs.getInt("id"),rs.getString("code"),rs.getString("designation"), rs.getDouble("prix"), 
																new Categorie(rs.getInt("id_categorie"),rs.getString("nom"))));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				statement.close();
				conn.close();
			}
			catch (SQLException e){
				e.printStackTrace();
			}
		}
		return ToutesMesPizzas;
	}
	public void clearTablePizza() {
		Connection conn = null;
		Statement st = null;

		try {
			conn = Connect.getConnection();
			st = conn.createStatement();
			st.execute("TRUNCATE TABLE `pizza`;");
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"******clearPizza*********");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage()+"******conn.close()---------------resetAllPizza*********");
			}
		}
	}
	
	public void resetAllPizza() {
		Connection conn = null;
		Statement st = null;
		try {
			clearTablePizza();
			conn = Connect.getConnection();
			st = conn.createStatement();
			st.execute("INSERT INTO `PIZZA` (`ID`, `CODE`, `DESIGNATION`, `PRIX`, `ID_CATEGORIE`) VALUES\r\n" + 
					"(1, 'PEP', 'PEPERONI', 7.2, 4),\r\n" + 
					"(2, 'MAR', 'MARGHERITA', 8.2, 1),\r\n" + 
					"(3, 'REI', 'LA REINE', 6.8, 1),\r\n" + 
					"(4, 'FRO', '4 FROMAGES', 8.1, 1),\r\n" + 
					"(5, 'CAN', 'CANADIENNE', 9.3, 2),\r\n" + 
					"(6, 'ORI', 'ORIENTALE', 10.4, 2),\r\n" + 
					"(7, 'IND', 'INDIENNE', 10.2, 4);");
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"******resetAllPizza*********");
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage()+"******conn.close()---------------resetAllPizza*********");
			}
		}
		
	}
	public boolean checkMaPizza(Pizza pizza) throws StockageException{
		boolean ifOk= true;
		String message = "";
		
		if(pizza == null) {
			message += "la pizza n'existe pas !";
		}else {
			if(pizza.getCode().trim().length()> CODE_LONG) {
				message += "le code de la pizza est trop long, \n\r";
			}if(pizza.getPrice()> PRICE_MAX) {
				message += "le prix de la pizza est trop grand, \n\r";
			}if(pizza.getPrice()< PRICE_MIN) {
				message += "le prix de la pizza est trop grand, \n\r";
			}
		}
		if(message.length()>0 && message != null) {
			ifOk = false;
			throw  new StockageException(message);
		}
			return ifOk;
	}

	public Pizza T(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pizza maPizza = null;
		try{
			conn = Connect.getConnection();
			ps = conn.prepareStatement("SELECT pizza.id, code, designation, prix , id_categorie, categorie.nom FROM pizza JOIN categorie ON pizza.id_categorie = categorie.id WHERE pizza.id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()){		
				maPizza = new Pizza(rs.getInt("id"),rs.getString("code"),rs.getString("designation"), rs.getDouble("prix"), 
						new Categorie(rs.getInt("id_categorie"),rs.getString("nom")));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			}
			catch (SQLException e){
				e.printStackTrace();
			}	
		}
		return maPizza;
	
	}

	public Pizza findOnePizza(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
