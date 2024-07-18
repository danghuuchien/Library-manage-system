package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import MyPack.Database;
import MyPack.GUI_LOGIN;

import java.awt.Color;
import javax.swing.JTextField;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UI_ChangePassword extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;
	private JTextField txtMaXN;
	public String verificationCode;
	
	public UI_ChangePassword() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(0, 50, 1008, 602);
		
		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(404, 51, 254, 73);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu cũ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(145, 189, 130, 47);
		add(lblNewLabel_1);
		
		txtOldPassword = new JPasswordField();
		txtOldPassword.setBounds(405, 197, 305, 39);
		add(txtOldPassword);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu mới");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(145, 322, 130, 47);
		add(lblNewLabel_1_1);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setBounds(405, 326, 305, 39);
		add(txtNewPassword);
		
		JButton btnNewButton = new JButton("XÁC NHẬN");
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(486, 527, 171, 39);
		add(btnNewButton);
		
		JLabel lbMa = new JLabel("Mã xác nhận");
		lbMa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbMa.setBounds(145, 423, 130, 47);
		add(lbMa);
		
		txtMaXN = new JTextField();
		txtMaXN.setBounds(404, 423, 306, 37);
		add(txtMaXN);
		txtMaXN.setColumns(10);
		
		EmailSending();
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Connection con = null;
		        PreparedStatement pstmt = null;
		        char[] oldPassword = txtOldPassword.getPassword();
		        String oldPasswordStr = String.valueOf(oldPassword);
		        if (txtMaXN.getText().equals(verificationCode)) {
			        if (oldPasswordStr.equals(oldPasswordStr) ) {
			            try {
			            	con = Database.Getconnect();
			            	String sql = "UPDATE ACCOUNT SET MatKhau = ? WHERE MaDocGia = ?";
			            	char[] newPassword = txtNewPassword.getPassword();
			            	String newPasswordStr = String.valueOf(newPassword);
			            	pstmt = con.prepareStatement(sql);
			            	pstmt.setString(1, newPasswordStr);
			            	pstmt.setString(2, GUI_LOGIN.maDocGia);
			            	pstmt.execute();
			            	
			                JOptionPane.showMessageDialog(null, "Thay đổi cột MatKhau thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
			            } catch (SQLException ex) {
			                JOptionPane.showMessageDialog(null, "Thay đổi cột MatKhau thất bại: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
			            } finally {
			                try {
			                    if (pstmt != null) pstmt.close();
			                } catch (SQLException ex) {
			                    System.out.println(ex.getMessage());
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng", "Thất bại", JOptionPane.INFORMATION_MESSAGE);
			            System.out.print(oldPasswordStr);
			        }
			    }
		        else {
		            JOptionPane.showMessageDialog(null, "Mã xác nhận không đúng", "Thất bại", JOptionPane.INFORMATION_MESSAGE);
		            System.out.print(verificationCode);
		        }
			}
		});
	}
	
	public void EmailSending()
	{
		String ToEmail = "daocatdai.in@gmail.com";
		String FromEmail = "daocatdai@gmail.com";
		String Password = "wald xiej pign umzp";
		String Subject = "Change Password project DoAn_Java";
		
		Properties props = new Properties();
		props.put("mail.smtp.user","username"); 
		props.put("mail.smtp.host", "smtp.gmail.com"); 
		props.put("mail.smtp.port", "25"); 
		props.put("mail.debug", "true"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.EnableSSL.enable","true");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
		props.setProperty("mail.smtp.socketFactory.fallback", "false");   
		props.setProperty("mail.smtp.port", "465");   
		props.setProperty("mail.smtp.socketFactory.port", "465"); 
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FromEmail, Password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(FromEmail));
		    message.addRecipient(Message.RecipientType.TO, new InternetAddress(ToEmail));
		    message.setSubject(Subject);
		    StringBuilder sb = new StringBuilder();
		    Random random = new Random();
		    for (int i = 0; i < 6; i++) {
		        int digit = random.nextInt(10);
		        sb.append(digit);
		    }

		    verificationCode = sb.toString();
		    message.setText("Đây là mã xác nhận của bạn: " + verificationCode);
		    Transport.send(message);
		} catch (Exception e) {
			System.out.print("" + e);
		}
	}
	
}
