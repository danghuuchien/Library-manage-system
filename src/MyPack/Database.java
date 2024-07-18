package MyPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private static String url = "jdbc:oracle:thin:@localhost:1521:";
    
    public static String database = "orcl";
    public static String user = "hr";
    public static String pass = "hr1234";
    public static Connection conn;
    
    public static boolean Connect(){
        try {
            if(user.equals("sys")||user.equals("SYS")){
                user += " as sysdba";
            }
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url+database, user, pass);
            return true;
        }catch(ClassNotFoundException | SQLException e){
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