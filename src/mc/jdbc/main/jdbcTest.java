package mc.jdbc.main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class jdbcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * connection base de donné Mysql
		 */

		
		// charger du pilote (driver) mysql
		//1-parametre de connection
		String connection_string = "jdbc:mysql://localhost:3306/java_db?user=root&password=Bstairway2heavenB&serverTimezone=UTC";
		try {
			Class.forName("com.mysql.jdbc.Driver"); // chargement du pilote selon son type
			System.out.println("Chargement driver OK!");
			
			//2-etablir la connection à la base de données
			Connection connection = DriverManager.getConnection(connection_string);
			System.out.println("Connection OK!");
			
			//3-a executer requete : exp  liste users(table)
			String sql= "SELECT nom,prenom,date_naissance , ville.nom FROM users ORDER BY nom,date_naissance";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); //execution sql + recup pointeur vers résulta !
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDate("date_naissance"));
			}
			rs.close();
			sql = "INSERT INTO users(prenom,nom)VALUES('John','DOE')";
			int resultat = stmt.executeUpdate(sql);
			System.out.println("Inseration ok! "+resultat);
			//3-c delete
			sql = "DELETE FROM users WHERE id>2 AND id<5";
			resultat = stmt.executeUpdate(sql);
			System.out.println("DELETE ok! "+resultat);
			
			//3-d
			sql = "UPDATE users SET prenom = 'Mehdi' WHERE id=1";
			resultat = stmt.executeUpdate(sql);
			System.out.println("Update ok! "+resultat);
			stmt.close();
			//4-fermmer la connection
			connection.close();
		}catch(Exception e) {
			System.out.println("Erreur : "+e);
		}
	}

}
