package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import MyPack.Database;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI_DangKy extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtDN;
	private JTextField txtMK;
	private JTextField txtMK_2;

	/**
	 * Create the panel.
	 */
	public UI_DangKy() {
		setBounds(0, 50, 605, 394);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG KÝ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(244, 10, 106, 42);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 104, 135, 21);
		getContentPane().add(lblNewLabel_1);
		
		txtDN = new JTextField();
		txtDN.setBounds(155, 104, 195, 30);
		getContentPane().add(txtDN);
		txtDN.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 188, 135, 21);
		getContentPane().add(lblNewLabel_1_1);
		
		txtMK = new JTextField();
		txtMK.setColumns(10);
		txtMK.setBounds(155, 188, 195, 30);
		getContentPane().add(txtMK);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nhập lại mật khẩu");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 274, 135, 21);
		getContentPane().add(lblNewLabel_1_2);
		
		txtMK_2 = new JTextField();
		txtMK_2.setColumns(10);
		txtMK_2.setBounds(155, 274, 195, 30);
		getContentPane().add(txtMK_2);
		
		JButton btnNewButton = new JButton("ĐĂNG KÝ");
		
		
		btnNewButton.setBounds(97, 323, 85, 21);
		getContentPane().add(btnNewButton);
		
		JButton btnHu = new JButton("HUỶ");
		btnHu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnHu.setBounds(382, 323, 85, 21);
		getContentPane().add(btnHu);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				CallableStatement cs1 = null;
			    CallableStatement cs2 = null;     
		        try {
		            con = Database.Getconnect();      
		            cs1 = con.prepareCall("INSERT INTO DOCGIA (MaDocGia) VALUES (?)");
		            cs1.setString(1, txtDN.getText());
		            // Thực thi câu lệnh INSERT vào bảng DOCGIA
		            cs1.execute();
		            
		            // Tạo CallableStatement cho câu lệnh INSERT vào bảng ACCOUNT
		            cs2 = con.prepareCall("INSERT INTO ACCOUNT (TenDangNhap, MaDocGia, MatKhau, Quyen) VALUES (?, ?, ?, 1)");
		            cs2.setString(1, txtDN.getText());
		            cs2.setString(2, txtDN.getText());
		            cs2.setString(3, txtMK.getText());
		            // Thực thi câu lệnh INSERT vào bảng ACCOUNT
		            cs2.execute();
		            
		            // Thực thi stored procedure
		            JOptionPane.showMessageDialog(null, "Đã đăng ký tài khoản ", "Thành công", JOptionPane.INFORMATION_MESSAGE);
		        }
		         catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Lỗi khi thêm dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		        finally {
		            try {
		                if (cs1 != null) cs1.close();
		                if (cs2 != null) cs2.close();
		            } catch (SQLException ex) {
		            }
		        }
			}
		});
		validatePassword();
	}
	public void validatePassword() {
	    String password = txtMK.getText();
	    String confirmPassword = txtMK_2.getText();
	    
	    if (!confirmPassword.equals(password)) {
	        JOptionPane.showMessageDialog(null, "Mật khẩu không khớp. Vui lòng nhập lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
