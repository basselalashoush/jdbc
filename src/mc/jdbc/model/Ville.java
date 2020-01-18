package mc.jdbc.model;

import java.util.List;

public class Ville {
	
	private int id;
	private String nom;
	private String cp;
	private List<User> users;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Ville(String nom, String cp, List<User> users) {
		this.nom = nom;
		this.cp = cp;
		this.users = users;
	}
	
	
	public Ville() {
		
	}
	
	public Ville(String nom, String cp) {
		this.nom = nom;
		this.cp = cp;
		
	}
	public Ville(int id,String nom, String cp) {
		this.id = id;
		this.nom = nom;
		this.cp = cp;
		
	}
	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", cp=" + cp + ", users=" + users + "]";
	}
	
	

}
