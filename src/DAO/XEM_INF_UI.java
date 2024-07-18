package DAO;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XEM_INF_UI extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JTextField txt_TnDN;
	public static JTextField txt_FullName;
	public static JTextField Txt_BOD;
	public static JTextField txt_DiaChi;
	public static JComboBox Txt_GT;

	/**
	 * Create the panel.
	 */
	public XEM_INF_UI() {
		setBounds(0, 0, 1008, 555);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(232, 11, 543, 533);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin cá nhân");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBounds(10, 11, 523, 48);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 59, 523, 432);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 39, 107, 27);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ và tên");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 93, 107, 27);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ngày sinh");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 148, 107, 27);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Địa chỉ");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(10, 206, 107, 27);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Giới tính");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(10, 263, 107, 27);
		panel_1.add(lblNewLabel_1_4);
		
		txt_TnDN = new JTextField();
		txt_TnDN.setEnabled(false);
		txt_TnDN.setEditable(false);
		txt_TnDN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_TnDN.setBounds(131, 39, 348, 25);
		panel_1.add(txt_TnDN);
		txt_TnDN.setColumns(10);
		
		txt_FullName = new JTextField();
		txt_FullName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_FullName.setColumns(10);
		txt_FullName.setBounds(131, 98, 348, 25);
		panel_1.add(txt_FullName);
		
		Txt_BOD = new JTextField();
		Txt_BOD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Txt_BOD.setColumns(10);
		Txt_BOD.setBounds(131, 153, 348, 25);
		panel_1.add(Txt_BOD);
		
		txt_DiaChi = new JTextField();
		txt_DiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_DiaChi.setColumns(10);
		txt_DiaChi.setBounds(131, 211, 348, 25);
		panel_1.add(txt_DiaChi);
		
		Txt_GT = new JComboBox();
		Txt_GT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Txt_GT.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		Txt_GT.setBounds(127, 263, 352, 26);
		panel_1.add(Txt_GT);
		
		JButton btnNewButton = new JButton("Đổi thông tin");
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(198, 502, 152, 23);
		panel.add(btnNewButton);
		//Event
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XEM_INF_DAO.updateINF();
			}
		});
		XEM_INF_DAO.showData();
	}
}
