package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import MyPack.DatabaseService;
import MyPack.GUI_LOGIN;
import oracle.jdbc.OracleTypes;


public class XEM_INF_DAO {
	public static void showData() {
		String maDocGia = GUI_LOGIN.maDocGia != null ? GUI_LOGIN.maDocGia.trim() : "";
        try (Connection connection = DatabaseService.GetConnection();
             CallableStatement callableStatement = connection.prepareCall("{call showDOCGIAx(?, ?)}")){
        	callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
        	callableStatement.setString(2, maDocGia);
        	callableStatement.execute();
        	try (ResultSet rs = (ResultSet) callableStatement.getObject(1)) {
                if (rs.next()) {
                    XEM_INF_UI.txt_TnDN.setText(rs.getString("TenDangNhap"));
                    XEM_INF_UI.txt_FullName.setText(rs.getString("HoTen"));
                    XEM_INF_UI.Txt_GT.setToolTipText(rs.getString("GioiTinh"));
                    XEM_INF_UI.Txt_BOD.setText(rs.getString("NamSinh")); // Assuming NamSinh is the column name for Date of Birth
                    XEM_INF_UI.txt_DiaChi.setText(rs.getString("DiaChi"));
                }
            }
        	DatabaseService.CloseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể truy cập");
        }
	}
	public static void updateINF() {
		String maDocGia = GUI_LOGIN.maDocGia != null ? GUI_LOGIN.maDocGia.trim() : "";
		String TenDN = XEM_INF_UI.txt_TnDN.getText();
	    String HoTen = XEM_INF_UI.txt_FullName.getText();
	    String GT = (String) XEM_INF_UI.Txt_GT.getSelectedItem(); 
	    String DiaChi = XEM_INF_UI.txt_DiaChi.getText();
	    String NamSinh = XEM_INF_UI.Txt_BOD.getText(); 
	    java.sql.Date namSinhDate = java.sql.Date.valueOf(NamSinh);
	    try (Connection connection = DatabaseService.GetConnection();
	         CallableStatement callableStatement = connection.prepareCall("{call updateDOCGIA(?, ?, ?, ?, ?, ?)}")) {
	    	callableStatement.setString(1, maDocGia);
			callableStatement.setString(2, TenDN);
			callableStatement.setString(3, HoTen);
			callableStatement.setString(4, GT);
			callableStatement.setDate(5, namSinhDate);
			callableStatement.setString(6, DiaChi);
	        callableStatement.execute();
	        JOptionPane.showMessageDialog(null, "Sửa thành công");
	        showData();
	        DatabaseService.CloseConnection();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Không thể truy cập");
	    }
	}
}
