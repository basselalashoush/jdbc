package mc.jdbc.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

import mc.jdbc.dao.UserDao;
import mc.jdbc.dao.VilleDao;
import mc.jdbc.model.User;
import mc.jdbc.model.Ville;

public class TestDao {

	static Scanner clavier = new Scanner(System.in);
	static VilleDao villeDao = new VilleDao();
	static UserDao userDao = new UserDao();
	static User userRole;;
	public static void main(String[] args) {


		while(userRole == null) {
			userRole = connecter();
			if(userRole == null) {
				System.out.println("identifiant incorrect !!");
			}
		}
		if(userRole != null) {
			System.out.println(userRole.getRole());
			int choix=0;
			do
			{
				choix = DisplayMenu(choix == 0);
				switch (choix)
				{
				case 1:
					getUsers();
					break;
				case 2:
					getVilles();
					break;
				case 3:
					addUser();
					break;
				case 4:
					addVille();
					break;
				case 5:
					editUser();
					break;
				case 6:
					editVille();
					break;
				case 7:
					deleteUser();
					break;
				case 8:
					deleteVille();
					break;
				default:
					if(choix!=9)
						choix = 0;
					break;
				}

			}
			while (choix!=9);
			System.out.println("Fin Gestion .");
		}else {
			System.out.println("identifiant incorrect !!");
		}
	}

	private static User connecter() {
		String login = lireClavier("Saisir login ");
		String pwd = lireClavier("Saisir password ");
		User user = userDao.login(login,pwd);
		if(user != null) {
			return user;
		}else {
			return null;
		}
	}


	private static void deleteVille() {
		String nom = lireClavier("Saisir nom de la ville ");
		Ville ville = villeDao.find(nom);
		if(ville != null) {
			villeDao.delete(ville);
		}else {
			System.out.println("la ville "+nom+" n'existe pas dans la base de données !!");
		}

	}

	private static void deleteUser() {
		String nom = lireClavier("Saisir nom user ");
		String prenom = lireClavier("Saisir prenom user ");
		User user = userDao.find(nom,prenom);
		if(user != null) {
			userDao.delete(user);
		}else {
			System.out.println("le user "+nom+" n'existe pas dans la base de données !!");
		}
	}

	private static void editVille() {
		String oldNom = lireClavier("Saisir nom de la ville à changer ");
		Ville ville = villeDao.find(oldNom);
		String nom = lireClavier("Saisir le new name de la ville  ");
		String cp = lireClavier("Saisir new cp de la ville  ");
		ville.setNom(nom);
		ville.setCp(cp);
		villeDao.update(ville);

	}

	private static void editUser() {
		String oldNom = lireClavier("Saisir nom ");
		String oldPrenom = lireClavier("Saisir prenom");
		User user = userDao.find(oldNom, oldPrenom);
		String nom = lireClavier("Saisir new name ");
		String prenom = lireClavier("Saisir new prenom");
		String date_naissance = lireClavier("Saisir new date de naissance ex : yyyy-mm-dd ");
		String login = lireClavier("Saisir login");
		String pwd = lireClavier("Saisir password ");
		String role = lireClavier("Saisir le role(user/admin)");
		String villeSaisie = lireClavier("Saisir new ville");
		if(isValidFormat("yyyy-MM-dd",date_naissance)) {
			LocalDate date = LocalDate.parse(date_naissance);
			Ville ville = villeDao.find(villeSaisie);
			if(ville == null) {
				String cp = lireClavier("Saisir le code postal");
				villeDao.add(new Ville(villeSaisie,cp));
				ville = villeDao.find(villeSaisie);
			}
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setDate_naissance(date);
			user.setLogin(login);
			user.setPwd(pwd);
			user.setRole(role);
			user.setVille(ville);
			userDao.update(user);
		}else {
			System.out.println("echoé !! la format de date incorrect");
		}


	}

	private static void addVille() {
		String nom = lireClavier("Saisir nom de la ville");
		String cp = lireClavier("Saisir le code postal");
		boolean ok = villeDao.add(new Ville(nom,cp));
		if(ok) {
			System.out.println("la ville a bien été enregistrée");
		}

	}

	private static void addUser() {
		String nom = lireClavier("Saisir nom ");
		String prenom = lireClavier("Saisir prenom");
		String date_naissance = lireClavier("Saisir date de naissance ex : yyyy-mm-dd");
		String login = lireClavier("Saisir login");
		String pwd = lireClavier("Saisir password ");
		String role = lireClavier("Saisir le role(user/admin)");
		String villeSaisie = lireClavier("Saisir la ville");

		Ville ville = villeDao.find(villeSaisie);

		if(ville == null) {
			String cp = lireClavier("Saisir le code postal");
			villeDao.add(new Ville(villeSaisie,cp));
			ville = villeDao.find(villeSaisie);
		}
		if(isValidFormat("yyyy-MM-dd",date_naissance)) {
			LocalDate date = LocalDate.parse(date_naissance);
			boolean flag = userDao.add(new User(nom,prenom,date,login,pwd,role,ville));
			if(flag) {
				System.out.println("le user a bien été enregistré");
			}
		}else {
			System.out.println("echoé !! la format de date incorrect");
		}

	}

	private static void getVilles() {
		List<Ville> villes = villeDao.list();
		for(Ville v : villes) {
			System.out.println(v);
		}

	}

	private static void getUsers() {
		List<User> users = userDao.list();
		for(User u : users) {
			System.out.println(u);
		}

	}

	private static int DisplayMenu(boolean complet)
	{
		int choix=0;
		String message;
		if (complet)
		{
			System.out.println(" ***************************************************************");
			System.out.println(" ********** Gestion users ==>  : *************");
			System.out.println(" ********** 1. Afficher users*********************************");
			System.out.println(" ********** 2. Afficher ville  ***********************");
			String admin = "admin";
			if(userRole.getRole().equalsIgnoreCase(admin)) {
				System.out.println(" ********** 3. Création user *****************************");
				System.out.println(" ********** 4. Création ville******************************");
				System.out.println(" ********** 5. Modifier user ******************************");
				System.out.println(" ********** 6. Modifier ville *****************************");
				System.out.println(" ********** 7. delete user ******************************");
				System.out.println(" ********** 8. delete ville ******************************");
			}
			System.out.println(" ********** 9. Quitter *****************************************");
			System.out.println(" ***************************************************************");

			message="===>";
		}
		else
		{
			message="Choix Menu ===>";
		}

		try {
			choix = Integer.parseInt(lireClavier(message));
		} catch (Exception e) {}

		return choix;
	}

	private static String lireClavier(String message)
	{        
		String saisie;
		do
		{
			System.out.print(message + " : ");
			saisie = clavier.nextLine();
		}
		while (saisie.equals(""));
		return saisie;
	}

	public static boolean isValidFormat(String format, String value) {
		LocalDate ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format);

		try {
			ldt = LocalDate.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			System.out.println("invalide date");

		}
		return false;
	}

}
