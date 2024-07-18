package MyPack;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardHomeHandler;

import DAO.DOI_MK_DAO;
import GUI.LichSuMuon;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main_Form extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Form frame = new Main_Form();
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
	public Main_Form() {
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
		
		JLabel Label_TroGiup = new JLabel("Trợ giúp");
		
		Label_TroGiup.setFont(new Font("Tahoma", Font.BOLD, 20));
		Label_TroGiup.setBounds(162, 0, 142, 73);
		HeaderPanel.add(Label_TroGiup);
		
		JPanel ViewPanel = new JPanel();
		
		ImageIcon imageIcon2 = new ImageIcon("D:/HomeWork/Doan_Java/i.png"); // load the image to a imageIcon
		Image image2 = imageIcon2.getImage(); // transform it 
		Image newimg2 = image2.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon2 = new ImageIcon(newimg2);  // transform it back
		Label_TroGiup.setIcon(imageIcon2);
		
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
		ViewPanel.setBounds(0, 77, 1008, 652);
		contentPane.add(ViewPanel);
		ViewPanel.setLayout(new CardLayout(0, 0));
		
		JPanel Home = new JPanel();
		ViewPanel.add(Home, "name_84859579625300");
		Home.setLayout(null);
		
		JLabel lblTmKimSch = new JLabel("Tìm kiếm sách");
		
		lblTmKimSch.setHorizontalAlignment(SwingConstants.CENTER);
		lblTmKimSch.setBounds(25, 11, 131, 40);
		lblTmKimSch.setFont(new Font("Tahoma", Font.BOLD, 15));
		ImageIcon imageIcon3 = new ImageIcon("D:/HomeWork/Doan_Java/search.png"); // load the image to a imageIcon
		Image image3 = imageIcon3.getImage(); // transform it 
		Image newimg3 = image3.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon3 = new ImageIcon(newimg3);  // transform it back
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 50, 1008, 602);
		Home.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		lblTmKimSch.setIcon(imageIcon3);
		Home.add(lblTmKimSch);
		
		JLabel lblThngTinC = new JLabel("Thông tin cá nhân");
		
		lblThngTinC.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngTinC.setFont(new Font("Tahoma", Font.BOLD, 15));
		ImageIcon imageIcon4 = new ImageIcon("D:/HomeWork/Doan_Java/user.png"); // load the image to a imageIcon
		Image image4 = imageIcon4.getImage(); // transform it 
		Image newimg4 = image4.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon4 = new ImageIcon(newimg4);
		lblThngTinC.setIcon(imageIcon4);
		lblThngTinC.setBounds(241, 11, 163, 40);
		Home.add(lblThngTinC);
		
		JLabel lblSchangMn = new JLabel("Sách đang mượn");
		lblSchangMn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchangMn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSchangMn.setBounds(478, 11, 131, 40);
		Home.add(lblSchangMn);
		
		JLabel lblLichSuM = new JLabel("Lịch sử mượn");
		lblLichSuM.setHorizontalAlignment(SwingConstants.CENTER);
		lblLichSuM.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLichSuM.setBounds(700, 11, 110, 40);
		Home.add(lblLichSuM);
		
		JLabel lblDoiMK = new JLabel("Đổi mật khẩu");
		lblDoiMK.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoiMK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDoiMK.setBounds(874, 11, 124, 40);
		Home.add(lblDoiMK);
		
		JPanel TroGiup1;
		TroGiup1 = new JPanel();
		ViewPanel.add(TroGiup1, "name_84886425955300");
		
		JPanel DangXuat = new JPanel();
		ViewPanel.add(DangXuat, "name_88295102734200");
		
		//Sự kiện label
		Label_Home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewHome();
			}
			private void viewHome() {
				Home.setVisible(true);
				TroGiup1.setVisible(false); 
				DangXuat.setVisible(false);
			}
		});
		
		Label_TroGiup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home.setVisible(false);
				TroGiup1.setVisible(true);
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
		
		lblTmKimSch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.removeAll(); 
				panel.add(new TimKiem.TimKiemSach());
				panel.revalidate(); 
				panel.repaint();
			}
		});
		
		lblThngTinC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.removeAll(); 
				panel.add(new DAO.XEM_INF_UI());
				panel.revalidate(); 
				panel.repaint();
			}
		});
		
		lblSchangMn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.removeAll(); 
				panel.add(new GUI.PhieuMuonFrame());
				panel.revalidate(); 
				panel.repaint();
			}
		});
		
		lblLichSuM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.removeAll(); 
				panel.add(new GUI.LichSuMuon());
				panel.revalidate(); 
				panel.repaint();
			}
		});
		
		lblDoiMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.removeAll(); 
				panel.add(new DAO.DOI_MK_UI());
				panel.revalidate(); 
				panel.repaint();
			}
		});
	}
}
