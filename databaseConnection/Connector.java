package databaseConnection;
import java.sql.*;

public class Connector {
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/?user=root";      
    private static String username = "root";   
    private static String password = "1234";
    private static Connection con;
    private static String driverName= "com.mysql.jdbc.Driver";

  
    public static Connection getConnection() {
        
            try {
            	Class.forName(driverName);
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
         
               e.printStackTrace();
            }     
            catch(ClassNotFoundException e){
            	e.printStackTrace();
            }
        
        return con;
    }


}
