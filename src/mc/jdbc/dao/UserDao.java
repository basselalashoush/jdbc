package mc.jdbc.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mc.jdbc.model.User;
import mc.jdbc.model.Ville;

public class UserDao implements IDao<User>{

	@Override
	public List<User> list() {
		List<User> users = new ArrayList<User>();
		User user;
		// récuperer liste User dans bas de données table users
		try {
			String sql= "SELECT * FROM users ";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); //execution sql + recup pointeur vers result !
			while(rs.next()) {
				// Mapping : associer enrgigistrement (table) à object de type User
				 Ville v = new VilleDao().find(rs.getInt("id_ville"));
				 LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				 user = new User(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),date,rs.getString("login"),rs.getString("pwd"),rs.getString("role"),v);
				 users.add(user);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return users;
	}

	@Override
	public User find(int id) {
		User user =null;
		try {
			String sql= "SELECT * FROM users WHERE id="+id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				// Mapping : associer enrgigistrement (table) à object de type User
				Ville v = new VilleDao().find(rs.getInt("id_ville"));
				 LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				 user = new User(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),date,rs.getString("login"),rs.getString("pwd"),rs.getString("role"),v);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return user;
	}

	@Override
	public boolean add(User item) {
		if(find(item.getNom(),item.getPrenom())!=null) {
			System.out.println("user '"+item.getNom()+"' existe déjà");
			return false;
		}
		int resultat = 0;
		try {
			String sql = "INSERT INTO users(nom,prenom,date_naissance,login,pwd,id_ville)VALUES('"+item.getNom().toUpperCase()+"','"+ucfirst(item.getPrenom())+"','"+item.getDate_naissance()+"','"+item.getLogin()+"','"+item.getPwd()+"','"+item.getRole()+"',"+item.getVille().getId()+")";
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean delete(User item) {
		int resultat = 0;
		try {
			String sql = "DELETE FROM users WHERE id="+item.getId();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	@Override
	public boolean update(User item) {
		int resultat = 0;
		try {
			String sql = "UPDATE users SET nom ='"+item.getNom()+"',prenom='"+item.getPrenom()+"',date_naissance='"+item.getDate_naissance()+"',login='"+item.getLogin()+"',pwd='"+item.getPwd()+"',role='"+item.getRole()+"',id_ville='"+item.getVille().getId()+"'"+"WHERE id="+item.getId();
			Statement stmt = connection.createStatement();
			resultat = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return (resultat>0);
	}

	public User find(String nom, String prenom) {
		User user =null;
		try {
			String sql= "SELECT * FROM users WHERE nom='"+nom+"' And prenom ='"+prenom+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				// Mapping : associer enrgigistrement (table) à object de type User
				Ville v = new VilleDao().find(rs.getInt("id_ville"));
				 LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				 user = new User(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),date,rs.getString("login"),rs.getString("pwd"),rs.getString("role"),v);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return user;
	}
	
	public User login(String login, String pwd) {
		User user =null;
		try {
			String sql= "SELECT * FROM users WHERE login='"+login+"' And pwd ='"+pwd+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			if(rs.next()) {
				// Mapping : associer enrgigistrement (table) à object de type User
				Ville v = new VilleDao().find(rs.getInt("id_ville"));
				 LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
				 user = new User(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),date,rs.getString("login"),rs.getString("pwd"),rs.getString("role"),v);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error : "+e);
		}
		return user;
	}

	@Override
	public String ucfirst(String chaine) {
		return chaine.substring(0, 1).toUpperCase()+ chaine.substring(1).toLowerCase();
	}


}
