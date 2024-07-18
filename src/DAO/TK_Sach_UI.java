package DAO;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import MyPack.DatabaseService;
import MyPack.GUI_LOGIN;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TK_Sach_UI extends JPanel {

	private static final long serialVersionUID = 1L;
	static JTable table;
    String database, user, pass;

	/**
	 * Create the frame.
	 */
	public TK_Sach_UI() {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 11, 970, 614);
		add(scrollPane);
		
		table = new JTable();	
				table.setCellSelectionEnabled(true);
				table.setColumnSelectionAllowed(true);
				table.setBorder(new LineBorder(new Color(0, 0, 0)));
				scrollPane.setViewportView(table);
		TK_SACH_DAO.showData();
	}
	public static void populateTable(ResultSet resultSet) throws SQLException {
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
        table.setModel(model);
    }
}
