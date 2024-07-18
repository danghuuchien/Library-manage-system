package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MyPack.DatabaseService;
import oracle.jdbc.OracleTypes;

public class TK_SACH_DAO {
	
	public static void showData() {
            try (Connection connection = DatabaseService.GetConnection();
                 CallableStatement callableStatement = connection.prepareCall("{call showSach(?)}")){
            	callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            	callableStatement.execute();
            	ResultSet rs = (ResultSet) callableStatement.getObject(1);
            	TK_Sach_UI.populateTable(rs);
            	DatabaseService.CloseConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Không thể truy cập");
	        }
	}
}


