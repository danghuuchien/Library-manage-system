package UI;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDayChooser;

import MyPack.Database;
import net.proteanit.sql.DbUtils;
import oracle.jdbc.OracleTypes;

import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import java.util.Date;
import java.util.Locale;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UI_ManagerMember extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMa;
	private JTextField txtDN;
	private JTextField txtHoTen;
	private JTextField txtDiaChi;
	private JDateChooser txtDate;
	private JRadioButton rdbNam;
	private JRadioButton rdbNu;

	Connection con = null;  
	private JTable table;
	private Object[] selectedRowData;
	public UI_ManagerMember() {
		setBounds(0, 50, 1008, 602);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 988, 298);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã độc giả");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 10, 102, 37);
		panel.add(lblNewLabel);
		
		txtMa = new JTextField();
		txtMa.setBounds(152, 10, 240, 27);
		panel.add(txtMa);
		txtMa.setColumns(10);
		
		JLabel lblTnngNhp = new JLabel("Tên đăng nhập");
		lblTnngNhp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTnngNhp.setBounds(10, 57, 123, 37);
		panel.add(lblTnngNhp);
		
		txtDN = new JTextField();
		txtDN.setColumns(10);
		txtDN.setBounds(152, 57, 240, 27);
		panel.add(txtDN);
		
		JLabel lblHTn = new JLabel("Họ tên");
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHTn.setBounds(10, 104, 102, 37);
		panel.add(lblHTn);
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(152, 104, 240, 27);
		panel.add(txtHoTen);
		
		JLabel lblNewLabel_1 = new JLabel("Địa chỉ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 251, 102, 37);
		panel.add(lblNewLabel_1);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(152, 259, 240, 27);
		panel.add(txtDiaChi);
		
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGiiTnh.setBounds(10, 151, 102, 37);
		panel.add(lblGiiTnh);
		
		JLabel lblNmSinh = new JLabel("Năm sinh");
		lblNmSinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNmSinh.setBounds(10, 204, 102, 37);
		panel.add(lblNmSinh);
		
		rdbNam = new JRadioButton("Nam");
		
		rdbNam.setBounds(152, 155, 60, 34);
		panel.add(rdbNam);
		
		rdbNu = new JRadioButton("Nữ");
		
		
		rdbNu.setBounds(266, 155, 60, 34);
		panel.add(rdbNu);
		
		txtDate = new JDateChooser();
		txtDate.setDateFormatString("yyyy-MM-dd");
	
		txtDate.setBounds(152, 214, 240, 27);
		panel.add(txtDate);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallableStatement cs = null;
				String maDocGia = txtMa.getText();
				String tenDangNhap = txtDN.getText();
				String hoTen = txtHoTen.getText();
				String gioiTinh = rdbNam.isSelected() ? "Nam" : "Nữ";
				Date namSinh = txtDate.getDate();
				String diaChi = txtDiaChi.getText();
				
			    try {
			        con = Database.Getconnect();
			        cs = con.prepareCall("INSERT INTO DOCGIA (TenDangNhap, MaDocGia, HoTen, GioiTinh, NamSinh, DiaChi) VALUES (?, ?, ?, ?, ?, ?)");
			        cs.setString(1, tenDangNhap);
			        cs.setString(2, maDocGia);		        
			        cs.setString(3, hoTen);
			        cs.setString(4, gioiTinh);
			        cs.setDate(5, new java.sql.Date(namSinh.getTime()));
			        cs.setString(6, diaChi);
			        
			        cs.executeUpdate();
			        
			        // Sau khi thêm dữ liệu thành công, cập nhật lại jTable
			        updateTable();
			        
			        // Xóa dữ liệu trong các trường nhập liệu
			        clearFields();
			        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			    } catch (Exception ex) {
			        System.out.println(ex.getMessage());
			        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			    } finally {
			        // Giải phóng tài nguyên
			        try {
			            if (cs != null) cs.close();
			            // Nếu cần, bạn có thể đóng cả kết nối ở đây
			        } catch (SQLException xe) {
			            System.out.println(xe.getMessage());
			        }
			    }
			}
		});
		
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print(Date());
			}
		});
		btnNewButton.setBounds(511, 115, 85, 21);
		panel.add(btnNewButton);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallableStatement cs = null;
				
				 String maDocGia = txtMa.getText();
			        String tenDangNhap = txtDN.getText();
			        String hoTen = txtHoTen.getText();
			        String gioiTinh = rdbNam.isSelected() ? "Nam" : "Nữ";
			        Date namSinh = txtDate.getDate();
			        String diaChi = txtDiaChi.getText();
			        int rowIndex = table.getSelectedRow();
			    try {
			        con = Database.Getconnect();
			        cs = con.prepareCall("UPDATE DOCGIA SET MaDocGia = ?, TenDangNhap = ?, HoTen = ?, GioiTinh = ?, NamSinh = ?, DiaChi = ? WHERE MaDocGia = ?");
			        cs.setString(1, maDocGia);
			        cs.setString(2, tenDangNhap);
			        cs.setString(3, hoTen);
			        cs.setString(4, gioiTinh);
			        cs.setDate(5, new java.sql.Date(namSinh.getTime()));
			        cs.setString(6, diaChi);
			        cs.setString(7, table.getValueAt(rowIndex, 0).toString());
			        
			        cs.executeUpdate();
			        
			        // Sau khi sửa dữ liệu thành công, cập nhật lại jTable
			        updateTable();
			        
			        // Xóa dữ liệu trong các trường nhập liệu
			        clearFields();
			        JOptionPane.showMessageDialog(null, "Sửa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			    } catch (Exception ex) {
			    	JOptionPane.showMessageDialog(null, "Sửa dữ liệu thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			    } finally {
			        // Giải phóng tài nguyên
			        try {
			            if (cs != null) cs.close();
			            // Nếu cần, bạn có thể đóng cả kết nối ở đây
			        } catch (SQLException ex) {
			            System.out.println(ex.getMessage());
			        }
			    }
			}
		});
		btnSa.setBounds(658, 115, 85, 21);
		panel.add(btnSa);
		
		JButton btnXo = new JButton("Xoá");
		btnXo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallableStatement cs = null;
				int rowIndex = table.getSelectedRow();
			    try {
			        con = Database.Getconnect();
			        cs = con.prepareCall("DELETE FROM DOCGIA WHERE MaDocGia = ?");
			        cs.setString(1, table.getValueAt(rowIndex, 0).toString());
			        
			        cs.executeUpdate();
			        
			        // Sau khi xoá dữ liệu thành công, cập nhật lại jTable
			        updateTable();
			        
			        // Xóa dữ liệu trong các trường nhập liệu
			        clearFields();
			        
			        // Hiển thị thông báo xoá thành công
			        JOptionPane.showMessageDialog(null, "Xoá dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			    } catch (Exception ex) {
			        System.out.println(ex.getMessage());
			        JOptionPane.showMessageDialog(null, "Xoá dữ liệu thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			    } finally {
			        // Giải phóng tài nguyên
			        try {
			            if (cs != null) cs.close();
			            // Nếu cần, bạn có thể đóng cả kết nối ở đây
			        } catch (SQLException ex) {
			            System.out.println(ex.getMessage());
			        }
			    }
			}
		});
		btnXo.setBounds(794, 115, 85, 21);
		panel.add(btnXo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
                int genderColumnIndex = 3; // Cột thứ 4 chứa giới tính

                String genderValue = (String) table.getValueAt(selectedRow, genderColumnIndex);
                if (genderValue.equals("Nam")) {
                    rdbNam.setSelected(true);
                    rdbNu.setSelected(false);
                } else if (genderValue.equals("Nữ")) {
                    rdbNam.setSelected(false);
                    rdbNu.setSelected(true);
                }
			}
		});
		scrollPane.setBounds(10, 318, 988, 274);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Lấy chỉ số dòng được chọn
		        int rowIndex = table.getSelectedRow();
		        
		        // Lấy dữ liệu của dòng được chọn
		        selectedRowData = new Object[table.getColumnCount()];
		        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
		            selectedRowData[columnIndex] = table.getValueAt(rowIndex, columnIndex);
		        }
		        
		        // Hiển thị dữ liệu trong các trường nhập liệu
		        displaySelectedData();
			}
		});
		scrollPane.setViewportView(table);
		
		openTable();

	}

	private String Date()
	{
		Date selectedDate = txtDate.getDate();

		// Định dạng ngày theo YYYY-MM-DD
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(selectedDate);
		return formattedDate;
	}
	private void openTable(){
		ResultSet rs = null;
        CallableStatement cs = null;
        try {
        	con = Database.Getconnect();
            cs = con.prepareCall("SELECT * FROM DOCGIA");
            cs.execute();

            rs = cs.getResultSet();
            
         // Tạo một mô hình bảng mặc định
            DefaultTableModel model = new DefaultTableModel();

            // Lấy thông tin về các cột từ ResultSet
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }

            // Thêm dữ liệu từ ResultSet vào mô hình bảng
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    rowData[columnIndex - 1] = rs.getObject(columnIndex);
                }
                model.addRow(rowData);
            }

            // Gán mô hình bảng cho jTable
            table.setModel(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Giải phóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                // Nếu cần, bạn có thể đóng cả kết nối ở đây
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }        
    }
	private void displaySelectedData() {
	    if (selectedRowData != null) {
	        // Hiển thị dữ liệu trong các trường nhập liệu
	        txtMa.setText(selectedRowData[0].toString());
	        txtDN.setText(selectedRowData[1].toString());
	        txtHoTen.setText(selectedRowData[2].toString());
	        
	        String gioiTinh = selectedRowData[3].toString();
	        if (gioiTinh.equals("Nam")) {
	            rdbNam.setSelected(true);
	        } else if (gioiTinh.equals("Nữ")) {
	            rdbNu.setSelected(true);
	        }
	        
	        // Lấy dữ liệu kiểu java.util.Date từ selectedRowData
	        Date namSinh = (Date) selectedRowData[4];
	        txtDate.setDate(namSinh);
	        
	        txtDiaChi.setText(selectedRowData[5].toString());
	    }
	}
	private void updateTable() {
	    ResultSet rs = null;
	    CallableStatement cs = null;
	    try {
	        con = Database.Getconnect();
	        cs = con.prepareCall("SELECT * FROM DOCGIA");
	        cs.execute();

	        rs = cs.getResultSet();

	        // Tạo một mô hình bảng mặc định
	        DefaultTableModel model = new DefaultTableModel();

	        // Lấy thông tin về các cột từ ResultSet
	        ResultSetMetaData metaData = rs.getMetaData();
	        int columnCount = metaData.getColumnCount();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            model.addColumn(metaData.getColumnLabel(columnIndex));
	        }

	        // Thêm dữ liệu từ ResultSet vào mô hình bảng
	        while (rs.next()) {
	            Object[] rowData = new Object[columnCount];
	            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	                rowData[columnIndex - 1] = rs.getObject(columnIndex);
	            }
	            model.addRow(rowData);
	        }

	        // Gán mô hình bảng cho jTable
	        table.setModel(model);

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    } finally {
	        // Giải phóng tài nguyên
	        try {
	            if (rs != null) rs.close();
	            if (cs != null) cs.close();
	            // Nếu cần, bạn có thể đóng cả kết nối ở đây
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	}
	private void clearFields() {
	    txtMa.setText("");
	    txtDN.setText("");
	    txtHoTen.setText("");
	    rdbNam.setSelected(true);
	    txtDate.setDate(null);
	    txtDiaChi.setText("");
	}
	
}
