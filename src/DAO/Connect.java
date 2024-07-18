package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {
  
    private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static String database = "QL_TV";
    public static String user = "hr";
    public static String pass = "hr1234";
    public static Connection conn;
    
    public static boolean Connect() {
        try {
            if (user.equals("sys") || user.equals("SYS")) {
                user += " as sysdba";
            }

           
            Class.forName("oracle.jdbc.driver.OracleDriver");

            
            conn = DriverManager.getConnection(url, user, pass);
            return true; 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    public static Connection Getconnect(){
        if(conn== null){
            Connect();
        }
        return conn;
    }
    
    public static void CloseConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws ClassNotFoundException {
    	Getconnect();
    }
}