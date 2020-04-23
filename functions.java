package finalProjectCS425;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class functions {

	
	public static Connection connect() {
		
		String database = "jdbc:postgresql://localhost/finalproject425";/*this is the location of the database when you saved the tables and stuff on postgress*/
		
		String user = "postgres";
		
		String password = "your password";/*whatever password you did for postgres set up*/
		
		try {
			
			Connection connection = DriverManager.getConnection(database, user, password);
			
			JOptionPane.showMessageDialog(null, "connected to database");
			
			return connection;
			
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}

}
