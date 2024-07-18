package MyPack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardHomeHandler;

import DAO.TK_Sach_UI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainAdmin_Form extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAdmin_Form frame = new MainAdmin_Form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainAdmin_Form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel HeaderPanel = new JPanel();
		HeaderPanel.setBounds(0, 0, 1008, 78);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);
		JLabel Label_Home = new JLabel("Home");
		Label_Home.setFont(new Font("Tahoma", Font.BOLD, 20));
		ImageIcon imageIcon = new ImageIcon("D:/HomeWork/Doan_Java/homeIcon.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		Label_Home.setIcon(imageIcon);
		Label_Home.setBounds(23, 0, 112, 73);
		HeaderPanel.add(Label_Home);
		
		JLabel lblQunL = new JLabel("Quản lý");
		
		lblQunL.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQunL.setBounds(165, 0, 142, 73);
		HeaderPanel.add(lblQunL);
		
		JPanel ViewPanel = new JPanel();
		
		JLabel Label_DangXuat = new JLabel("Đăng xuất");
		ImageIcon imageIcon1 = new ImageIcon("D:/HomeWork/Doan_Java/logout.png"); // load the image to a imageIcon
		Image image1 = imageIcon1.getImage(); // transform it 
		Image newimg1 = image1.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon1 = new ImageIcon(newimg1);  // transform it back
		Label_DangXuat.setIcon(imageIcon1);
		
		Label_DangXuat.setHorizontalAlignment(SwingConstants.CENTER);
		Label_DangXuat.setFont(new Font("Tahoma", Font.BOLD, 20));
		Label_DangXuat.setBounds(820, 0, 178, 73);
		HeaderPanel.add(Label_DangXuat);
		
		JLabel Label_ThongKe = new JLabel("Thống kê");
		Label_ThongKe.setFont(new Font("Tahoma", Font.BOLD, 20));
		Label_ThongKe.setBounds(314, 0, 142, 73);
		HeaderPanel.add(Label_ThongKe);
		ViewPanel.setBounds(0, 77, 1008, 652);
		contentPane.add(ViewPanel);
		ViewPanel.setLayout(new CardLayout(0, 0));
		
		JPanel Home = new JPanel();
		ViewPanel.add(Home, "name_84859579625300");
		Home.setLayout(null);
		ImageIcon imageIcon3 = new ImageIcon("D:/HomeWork/Doan_Java/search.png"); // load the image to a imageIcon
		Image image3 = imageIcon3.getImage(); // transform it 
		Image newimg3 = image3.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon3 = new ImageIcon(newimg3);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 50, 1008, 602);
		Home.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		ImageIcon imageIcon4 = new ImageIcon("D:/HomeWork/Doan_Java/user.png"); // load the image to a imageIcon
		Image image4 = imageIcon4.getImage(); // transform it 
		Image newimg4 = image4.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon4 = new ImageIcon(newimg4);
		
		JLabel lblDoiMK = new JLabel("Đổi mật khẩu");
		lblDoiMK.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoiMK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDoiMK.setBounds(10, 10, 124, 40);
		Home.add(lblDoiMK);
		
		JPanel QuanLy;
		QuanLy = new JPanel();
		ViewPanel.add(QuanLy, "name_84886425955300");
		QuanLy.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 50, 1008, 602);
		QuanLy.add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JLabel lblQL_ThanhVien = new JLabel("Quản lý thành viên");
		lblQL_ThanhVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.removeAll(); 
				panel_1.add(new UI.UI_ManagerMember());
				panel_1.revalidate(); 
				panel_1.repaint();
			}
		});
		lblQL_ThanhVien.setHorizontalAlignment(SwingConstants.CENTER);
		lblQL_ThanhVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQL_ThanhVien.setBounds(23, 11, 161, 40);
		QuanLy.add(lblQL_ThanhVien);
		
		JLabel lblQunLSach = new JLabel("Quản lý sách");
		
		lblQunLSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLSach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQunLSach.setBounds(267, 11, 124, 40);
		QuanLy.add(lblQunLSach);
		
		JLabel lblQunLPhiu = new JLabel("Quản lý phiếu mượn");
		lblQunLPhiu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.removeAll(); 
				panel_1.add(new TimKiem.PhieuMuon());
				panel_1.revalidate(); 
				panel_1.repaint();
				
			}
		});
		lblQunLPhiu.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLPhiu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQunLPhiu.setBounds(534, 11, 169, 40);
		QuanLy.add(lblQunLPhiu);
		
		JLabel lblQunLPhiu_1 = new JLabel("Quản lý phiếu trả");
		lblQunLPhiu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.removeAll(); 
				panel_1.add(new GUI.QLPhieuTraFrame());
				panel_1.revalidate(); 
				panel_1.repaint();
				
				
				
			}
		});
		lblQunLPhiu_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLPhiu_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQunLPhiu_1.setBounds(809, 11, 161, 40);
		QuanLy.add(lblQunLPhiu_1);
		
		JPanel DangXuat = new JPanel();
		ViewPanel.add(DangXuat, "name_88295102734200");
		
		JPanel ThongKe = new JPanel();
		ViewPanel.add(ThongKe, "name_90928832947500");
		ThongKe.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 47, 1008, 555);
		ThongKe.add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));
		
		JLabel lblTK_Sach = new JLabel("Thống kê sách");
		lblTK_Sach.setBackground(Color.RED);
		
		lblTK_Sach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTK_Sach.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTK_Sach.setBounds(39, 11, 161, 40);
		ThongKe.add(lblTK_Sach);
		
		JLabel lblTK_DocGia = new JLabel("Thống kê đọc giả");
		
		lblTK_DocGia.setHorizontalAlignment(SwingConstants.CENTER);
		lblTK_DocGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTK_DocGia.setBounds(261, 11, 161, 40);
		ThongKe.add(lblTK_DocGia);
		lblTK_DocGia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_2.removeAll(); 
				panel_2.add(new DAO.TK_DOCGIA_UI());
				panel_2.revalidate();
				panel_2.repaint();
			}
		});
		
		//Sự kiện label
		Label_Home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewHome();
			}
			private void viewHome() {
				Home.setVisible(true);
				QuanLy.setVisible(false);
				ThongKe.setVisible(false);
				DangXuat.setVisible(false);
			}
		});
		
		lblQunL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.setVisible(false);
				QuanLy.setVisible(true);
				ThongKe.setVisible(false);
				DangXuat.setVisible(false);
			}
		});
		
		Label_DangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DatabaseService.CloseConnection();
            	GUI_LOGIN login = new GUI_LOGIN();
            	setVisible(false);
            	login.setVisible(true);
			}
		});
		
		Label_ThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.setVisible(false);
				QuanLy.setVisible(false);
				ThongKe.setVisible(true);
				DangXuat.setVisible(false);
			}
		});
		
		lblDoiMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Bạn muốn thay đổi mật khẩu?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		        if (option == JOptionPane.YES_OPTION) {
		            panel.removeAll();
		            panel.add(new UI.UI_ChangePassword());
		            panel.revalidate();
		            panel.repaint();
		            
		            JOptionPane.showMessageDialog(null, "Chúng tôi đã gửi mã xác nhận đến Gmail: tran****02@gmail.com để đổi mật khẩu đồ án", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        }
			}
		});
		lblTK_Sach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_2.removeAll(); 
				panel_2.add(new DAO.TK_Sach_UI());
				panel_2.revalidate();
				panel_2.repaint();
				
			}
		});
		lblQunLSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.removeAll(); 
				panel_1.add(new DAO.QL_SACH_UI());
				panel_1.revalidate(); 
				panel_1.repaint();
			}
		});
	}
}
