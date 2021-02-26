package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SingleConnection {

	String db="produitmagasin";
	String user="root";
	String pwd="";
	String url="jdbc:mysql://localhost:3306/";
	private static Connection connection=null;
	private SingleConnection() {
		try {
			 connection = DriverManager.getConnection(url+db+"?useTimezone=true&serverTimezone=UTC", user, pwd);
				}catch (Exception ex) {
					ex.printStackTrace();
				}
	}
	public static Connection getConnection() {
		//if (connection == null) 
		 new SingleConnection();
		
		return connection;
	}
}
