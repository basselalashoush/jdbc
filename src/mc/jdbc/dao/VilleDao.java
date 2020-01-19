package mc.jdbc.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mc.jdbc.model.Ville;

public class VilleDao implements IDao<Ville>{

	@Override
	public List<Ville> list() {
		List<Ville> villes = new ArrayList<Ville>();
		Ville ville;
		// récuperer liste ville dans bas de données table villes
		try {
			String sql= "SELECT * FROM villes ";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); //execution sql + recup pointeur vers résulta !
			while(rs.next()) {
				// Mapping : associer enrgigistrement (table) à objet de type ville
				 ville = new Ville(rs.getInt("id"),rs.getString("nom"),rs.getString("cp"));
				 villes.add(ville);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return villes;
	}

	@Override
	public Ville find(int id) {
		Ville ville =null;
		// récuperer une ville dans bas de données table villes/ id
		try {
			String sql= "SELECT * FROM villes WHERE id="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); //execution sql + recup pointeur vers résulta !
			if(rs.next()) {
				// Mapping : associer enrgigistrement (table) à objet de type ville
				 ville = new Ville(rs.getInt("id"),rs.getString("nom"),rs.getString("cp"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return ville;
	}

	@Override
	public boolean add(Ville item) {
		if(find(ucfirst(item.getNom().toLowerCase()))!=null) {
			System.out.println("la ville '"+item.getNom()+"' existe déjà");
			return false;
		}
		int resultat = 0;
		try {
			String sql = "INSERT INTO villes(nom,cp)VALUES('"+ucfirst(item.getNom().toLowerCase())+"','"+item.getCp()+"')";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(Ville item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM villes WHERE id="+item.getId();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(Ville item) {
		int resultat = 0;
		try {
			String sql = "UPDATE villes SET nom ='"+ucfirst(item.getNom().toLowerCase())+"',cp='"+item.getCp()+"'"+"WHERE id="+item.getId();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}
	
	// methodes spécifiques
	
	public Ville find(String nom) {
		Ville ville =null;
		// récuperer une ville dans bas de données table villes/ id
		try {
			String sql= "SELECT * FROM villes WHERE nom='"+nom.toLowerCase()+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); //execution sql + recup pointeur vers résulta !
			if(rs.next()) {
				// Mapping : associer enrgigistrement (table) à objet de type ville
				 ville = new Ville(rs.getInt("id"),rs.getString("nom"),rs.getString("cp"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return ville;
	}
	
	@Override
	public String ucfirst(String chaine) {
		return chaine.substring(0, 1).toUpperCase()+ chaine.substring(1).toLowerCase();
	}

}
