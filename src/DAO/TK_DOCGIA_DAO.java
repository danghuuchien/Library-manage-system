package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import MyPack.DatabaseService;
import oracle.jdbc.OracleTypes;

public class TK_DOCGIA_DAO {
	public static void showData() {
        try (Connection connection = DatabaseService.GetConnection();
             CallableStatement callableStatement = connection.prepareCall("{call showDOCGIA(?)}")){
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
	    TK_DOCGIA_UI.table.setModel(model);
	}
}
