package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import MyPack.DatabaseService;
import oracle.jdbc.OracleTypes;

public class QL_SACH_DAO {
	
	public static void showData() {
            try (Connection connection = DatabaseService.GetConnection();
                 CallableStatement callableStatement = connection.prepareCall("{call showSach(?)}")){
            	callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            	callableStatement.execute();
            	ResultSet rs = (ResultSet) callableStatement.getObject(1);
            	populateTable(rs);
            	DatabaseService.CloseConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Không thể truy cập");
	        }
	}
	public static void addData() {
	    String maSach = QL_SACH_UI.txt_maSach.getText();
	    String tenSach = QL_SACH_UI.txt_tenSach.getText();
	    String tacGia = QL_SACH_UI.txt_tenTG.getText(); 
	    String theLoai = QL_SACH_UI.txt_theLoai.getText();
	    String nhaXuatBan = QL_SACH_UI.txt_NXB.getText(); 
	    int giaSach = (int) Double.parseDouble(QL_SACH_UI.txt_GiaSach.getText()); 
	    int soLuong = Integer.parseInt(QL_SACH_UI.txt_SoLuong.getText()); 
	    String tinhTrang = QL_SACH_UI.txt_TinhTrang.getText(); 
	    try (
	        Connection connection = DatabaseService.GetConnection();
	        CallableStatement callableStatement = connection.prepareCall("{call insertSach(?, ?, ?, ?, ?, ?, ?, ?)}")) {
	        callableStatement.setString(1, maSach);
	        callableStatement.setString(2, tenSach);
	        callableStatement.setString(3, tacGia);
	        callableStatement.setString(4, theLoai);
	        callableStatement.setString(5, nhaXuatBan);
	        callableStatement.setInt(6, giaSach);
	        callableStatement.setInt(7, soLuong);
	        callableStatement.setString(8, tinhTrang);
	        callableStatement.execute();
	        JOptionPane.showMessageDialog(null, "Thêm thành công");
	        showData();
	        DatabaseService.CloseConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Không thể truy cập");
	    }
	}

	

	public static void deleteDataTable() {
		String maSach = QL_SACH_UI.txt_maSach.getText();
		try (Connection connection = DatabaseService.GetConnection();
                CallableStatement callableStatement = connection.prepareCall("{call delete_sach(?)}")){
           	callableStatement.setString(1, maSach);
           	callableStatement.execute();
           	JOptionPane.showMessageDialog(null, "Xoá thành công");
            showData();
            DatabaseService.CloseConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Không thể truy cập");
	        }
	}
	
	public static void updateDataTable() {
		String maSach = QL_SACH_UI.txt_maSach.getText();
		String tenSach = QL_SACH_UI.txt_tenSach.getText();
        String tacGia = QL_SACH_UI.txt_tenTG.getText(); 
        String theLoai = QL_SACH_UI.txt_theLoai.getText();
        String nhaXuatBan = QL_SACH_UI.txt_NXB.getText(); 
        int giaSach = (int) Double.parseDouble(QL_SACH_UI.txt_GiaSach.getText()); 
        int soLuong = Integer.parseInt(QL_SACH_UI.txt_SoLuong.getText()); 
        String tinhTrang = QL_SACH_UI.txt_TinhTrang.getText(); 
        try (
            Connection connection = DatabaseService.GetConnection(); // Get the connection
            CallableStatement callableStatement = connection.prepareCall("{call update_sach(?, ?, ?, ?, ?, ?, ?, ?)}")) {
        		callableStatement.setString(1, maSach);
                callableStatement.setString(2, tenSach);
                callableStatement.setString(3, tacGia);
                callableStatement.setString(4, theLoai);
                callableStatement.setString(5, nhaXuatBan);
                callableStatement.setLong(6, giaSach);
                callableStatement.setLong(7, soLuong);
                callableStatement.setString(8, tinhTrang);
                callableStatement.execute();
                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                showData();
                DatabaseService.CloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Không thể truy cập");
            }
	}
	
	private static void populateTable(ResultSet resultSet) throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        // Thêm các cột vào model dựa trên metadata của ResultSet
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            model.addColumn(resultSet.getMetaData().getColumnName(i));
        }
        // Thêm dữ liệu từ ResultSet vào model
        while (resultSet.next()) {
            Object[] rowData = new Object[resultSet.getMetaData().getColumnCount()];
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                rowData[i - 1] = resultSet.getObject(i);
            }
            model.addRow(rowData);
        }
        // Hiển thị dữ liệu trên bảng
        QL_SACH_UI.table.setModel(model);
    }
}


