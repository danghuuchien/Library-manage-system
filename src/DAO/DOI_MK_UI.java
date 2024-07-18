package DAO;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DOI_MK_UI extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JTextField txt_MKcu;
	public static JTextField txt_MKmoi;

	/**
	 * Create the panel.
	 */
	public DOI_MK_UI() {
		setBounds(0, 0, 1008, 555);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(187, 11, 633, 533);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đổi mật khẩu");
		lblNewLabel.setBounds(10, 6, 613, 43);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Đổi mật khẩu");

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(217, 489, 199, 33);
		
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 60, 613, 423);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu cũ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 109, 174, 33);
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu mới");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(10, 200, 174, 33);
		lblNewLabel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txt_MKcu = new JTextField();
		txt_MKcu.setBounds(194, 109, 409, 33);
		panel_1.add(txt_MKcu);
		txt_MKcu.setColumns(10);
		
		txt_MKmoi = new JTextField();
		txt_MKmoi.setColumns(10);
		txt_MKmoi.setBounds(194, 200, 409, 33);
		panel_1.add(txt_MKmoi);
		//Event
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DOI_MK_DAO.DoiMK();
			}
		});
	}
}
