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

public class QL_SACH_UI extends JPanel {

	private static final long serialVersionUID = 1L;
	static JTextField txt_tenSach;
	static JTextField txt_tenTG;
	static JTextField txt_theLoai;
	static JTextField txt_TinhTrang;
	static JTextField txt_NXB;
	static JTextField txt_GiaSach;
	static JTextField txt_SoLuong;
	static JTable table;
	static JTextField txt_maSach;
    String database, user, pass;

	/**
	 * Create the frame.
	 */
	public QL_SACH_UI() {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		setBounds(0, 0, 1008, 555);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 972, 189);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên sách");
		lblNewLabel.setBounds(51, 60, 72, 28);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setBounds(51, 99, 72, 28);
		panel.add(lblTcGi);
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setBounds(51, 138, 72, 28);
		panel.add(lblThLoi);
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNhXutBn = new JLabel("Nhà xuất bản");
		lblNhXutBn.setBounds(568, 20, 101, 28);
		panel.add(lblNhXutBn);
		lblNhXutBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblGiSch = new JLabel("Giá sách");
		lblGiSch.setBounds(568, 59, 72, 28);
		panel.add(lblGiSch);
		lblGiSch.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setBounds(568, 98, 72, 28);
		panel.add(lblSLng);
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblTnhTrng = new JLabel("Tình trạng");
		lblTnhTrng.setBounds(568, 138, 72, 28);
		panel.add(lblTnhTrng);
		lblTnhTrng.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txt_tenSach = new JTextField();
		txt_tenSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_tenSach.setBounds(133, 57, 202, 31);
		panel.add(txt_tenSach);
		txt_tenSach.setColumns(10);
		
		txt_tenTG = new JTextField();
		txt_tenTG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_tenTG.setBounds(133, 96, 202, 31);
		panel.add(txt_tenTG);
		txt_tenTG.setColumns(10);
		
		txt_theLoai = new JTextField();
		txt_theLoai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_theLoai.setBounds(133, 135, 202, 31);
		panel.add(txt_theLoai);
		txt_theLoai.setColumns(10);
		
		txt_TinhTrang = new JTextField();
		txt_TinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_TinhTrang.setBounds(689, 137, 202, 31);
		panel.add(txt_TinhTrang);
		txt_TinhTrang.setColumns(10);
		
		txt_NXB = new JTextField();
		txt_NXB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_NXB.setBounds(689, 21, 202, 28);
		panel.add(txt_NXB);
		txt_NXB.setColumns(10);
		
		txt_GiaSach = new JTextField();
		txt_GiaSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_GiaSach.setBounds(689, 60, 202, 28);
		panel.add(txt_GiaSach);
		txt_GiaSach.setColumns(10);
		
		txt_SoLuong = new JTextField();
		txt_SoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_SoLuong.setBounds(689, 99, 202, 28);
		panel.add(txt_SoLuong);
		txt_SoLuong.setColumns(10);
		
		JLabel lblMSch = new JLabel("Mã sách");
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMSch.setBounds(51, 21, 72, 28);
		panel.add(lblMSch);
		
		txt_maSach = new JTextField();
		txt_maSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_maSach.setColumns(10);
		txt_maSach.setBounds(133, 18, 202, 31);
		panel.add(txt_maSach);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(11, 200, 970, 344);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 968, 288);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            Object Masach = table.getValueAt(selectedRow, 0);
		            txt_maSach.setText(String.valueOf(Masach));
		            Object TenSach = table.getValueAt(selectedRow, 1);
		            txt_tenSach.setText(String.valueOf(TenSach));
		            Object TacGia = table.getValueAt(selectedRow, 2);
		            txt_tenTG.setText(String.valueOf(TacGia));
		            Object TheLoai = table.getValueAt(selectedRow, 3);
		            txt_theLoai.setText(String.valueOf(TheLoai));
		            Object NXB = table.getValueAt(selectedRow, 4);
		            txt_NXB.setText(String.valueOf(NXB));
		            Object GIA = table.getValueAt(selectedRow, 5);
		            txt_GiaSach.setText(String.valueOf(GIA));
		            Object SoLuong = table.getValueAt(selectedRow, 6);
		            txt_SoLuong.setText(String.valueOf(SoLuong));
		            Object TinhTrang = table.getValueAt(selectedRow, 7);
		            txt_TinhTrang.setText(String.valueOf(TinhTrang));
		        }
		    }
		});

		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Thêm");
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(123, 299, 127, 34);
		panel_1.add(btnNewButton);
		
		JButton btnXa = new JButton("Xóa");
		
		btnXa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXa.setBounds(393, 299, 127, 34);
		panel_1.add(btnXa);
		
		JButton btnSa = new JButton("Sửa");
		
		btnSa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSa.setBounds(650, 299, 127, 34);
		panel_1.add(btnSa);
		//Action
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_SACH_DAO.addData();
			}
		});
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_SACH_DAO.deleteDataTable();
			}
		});
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_SACH_DAO.updateDataTable();
			}
		});
		QL_SACH_DAO.showData();
	}
}
