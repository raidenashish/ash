package FAQ.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectToDB {
	private Connection c;
	public connectToDB(){
		try {
			String urli = "jdbc:mysql://localhost/faq";
		    String username = "root";
		    String password = "abcd";
		    this.c = DriverManager.getConnection( urli, username, password );
		    
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public Connection getC() {
		return this.c;
	}
	public void closeConnection(Connection c){
		try{
			c.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
}
