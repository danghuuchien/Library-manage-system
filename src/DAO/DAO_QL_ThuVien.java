package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class DAO_QL_ThuVien {
	 private Connection conn;
	 private static final String SERVER = "localhost"; // Địa chỉ máy chủ Oracle
	 private static final String USER = "hr"; // Tên người dùng Oracle
	 private static final String PASSWORD = "hr1234"; // Mật khẩu người dùng Oracle
	 private static final String DATABASE = "QL_TV"; // Tên cơ sở dữ liệu Oracle
	 private static final int PORT = 1521;
	 public static Connection open() {
		    try {
		        OracleDataSource ds = new OracleDataSource();
		        ds.setUser(USER);
		        ds.setPassword(PASSWORD);
		        ds.setDatabaseName(DATABASE);
		        ds.setServerName(SERVER);
		        ds.setPortNumber(PORT);
		        return ds.getConnection();
		    } catch (SQLException e) {
		        System.out.println("Database connection error: " + e.getMessage());
		    }
		    return null;
		}

	    public void close() {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                System.out.println("Error closing connection: " + e.getMessage());
	            }
	        }
	    }

	    public ResultSet executeQuery(String sql) {
	        ResultSet rs = null;
	        try{
	            Statement sm = conn.createStatement();
	            rs = sm.executeQuery(sql);
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return rs;
	    }

	    public int executeUpdate(String sql) {
	        int n = -1;
	        try{
	            Statement sm = conn.createStatement();
	            n = sm.executeUpdate(sql);
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return n;
	    }
}
