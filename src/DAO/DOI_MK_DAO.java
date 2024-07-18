package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import MyPack.DatabaseService;
import MyPack.GUI_LOGIN;
import oracle.jdbc.OracleTypes;

public class DOI_MK_DAO {
	public static void DoiMK() {
	    String maDocGia = GUI_LOGIN.maDocGia != null ? GUI_LOGIN.maDocGia.trim() : "";
	    String mkMoi = DOI_MK_UI.txt_MKmoi.getText() != null ? DOI_MK_UI.txt_MKmoi.getText().trim() : "";
	    String mkCu = DOI_MK_UI.txt_MKcu.getText() != null ? DOI_MK_UI.txt_MKcu.getText().trim() : "";
	    if (maDocGia.isEmpty() || mkMoi.isEmpty() || mkCu.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Không để trống dữ liệu");
	        return;
	    }else {
	        try (Connection connection = DatabaseService.GetConnection();
	            CallableStatement callableStatement = connection.prepareCall("{call DOI_MK(?, ?, ?)}")){
	        	callableStatement.setString(1, maDocGia);
	        	callableStatement.setString(2, mkCu);
	        	callableStatement.setString(3, mkMoi);
	        	callableStatement.execute();
	        	JOptionPane.showMessageDialog(null, "Đổi thành công");
	        	DatabaseService.CloseConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Không thể truy cập");
	        }
	    }
	}
}
