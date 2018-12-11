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
import com.pizzeria.model.CategorieDao;
import com.pizzeria.classe.Categorie;



	public class CategorieDao {
		public ArrayList<Categorie> getAllCategorie() {
			Connection conn = null;
			Statement statement = null;
			ResultSet rs = null;
			ArrayList<Categorie> myAllCategorie = new ArrayList<Categorie>();
			try{
				conn = Connect.getConnection();
				statement = conn.createStatement();
				rs = statement.executeQuery("SELECT  id, nom FROM categorie");

				while (rs.next()){
					myAllCategorie.add(new Categorie(rs.getInt("id"),rs.getString("nom")));
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
			return myAllCategorie;
		}
		public Categorie getOneCategorie(int id) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Categorie maCategorie = null;
			try{
				conn = Connect.getConnection();
				ps = conn.prepareStatement("SELECT  id, nom FROM categorie WHERE id = ?");
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				while (rs.next()){		
					maCategorie = new Categorie(rs.getInt("id"),rs.getString("nom"));
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
			
			return maCategorie;
		}
	}
