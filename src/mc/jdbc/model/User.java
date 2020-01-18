package mc.jdbc.model;

import java.time.LocalDate;



public class User {

	private int id;
	private String nom;
	private String prenom;
	private LocalDate date_naissance;
	private String login;
	private String pwd;
	private String role;
	private Ville ville;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public LocalDate getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(LocalDate date_naissance) {
		this.date_naissance = date_naissance;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	public int getId() {
		return id;
	}


	public User() {

	}
	
	
	public User(String nom, String prenom, LocalDate date_naissance, String login, String pwd,String role , Ville ville) {
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.login = login;
		this.pwd = pwd;
		this.role = role;
		this.ville = ville;
	}
	public User(String nom, String prenom, LocalDate date_naissance, Ville ville) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.ville = ville;
	}

	public User(int id,String nom, String prenom, LocalDate date_naissance , String login, String pwd,String role , Ville ville) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.login = login;
		this.pwd = pwd;
		this.role = role;
		this.ville = ville;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance
				+ ", login=" + login + ", pwd=" + pwd + ", role=" + role + ", ville=" + ville + "]";
	}



}
