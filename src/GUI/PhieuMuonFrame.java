package GUI;

import java.awt.EventQueue;
import MyPack.Database;
import MyPack.GUI_LOGIN;
import DAO.LichSuMuonDAO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import POJO.Sach;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.util.Vector;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.FlowLayout;
import com.toedter.components.JLocaleChooser;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import DAO.PhieuMuonDAO;
import POJO.PhieuMuon;


public class PhieuMuonFrame extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField searchField;
	private JTable table;
	private TableRowSorter<DefaultTableModel> sorter;
	private JLabel Image1;
	private JLabel image2;
	private JLabel image3;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
	                if (Database.Connect()) {
	                    System.out.println("Kết nối tới cơ sở dữ liệu thành công.");
	                     
	                    PhieuMuonFrame frame = new PhieuMuonFrame(); 
	                    frame.setVisible(true);
	                } else {
	                    System.out.println("Không thể kết nối tới cơ sở dữ liệu.");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PhieuMuonFrame() {

		setBounds(100, 100, 850, 592);
		setBorder(new EmptyBorder(5, 5, 5, 5));


		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tìm tên sách");
		lblNewLabel.setBounds(167, 83, 77, 15);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewLabel);
		
		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(270, 77, 316, 28);
		add(searchField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 128, 735, 110);
		add(scrollPane);
		
		
	     table = new JTable();
		
		scrollPane.setColumnHeaderView(table);
		 scrollPane.setViewportView(table);
		 
		//căn giữa cột
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	    for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
	            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
	        }
	        
	        
	        Image1 = new JLabel("");
	        //Image1.setIcon(new ImageIcon(SachMuon.class.getResource("/Image/Picture12.png")));
	        ImageIcon originalIcon = new ImageIcon(PhieuMuonFrame.class.getResource("/Image/Picture12.png"));
	        Image image = originalIcon.getImage().getScaledInstance(180, 190, Image.SCALE_SMOOTH);
	        Image1.setIcon(new ImageIcon(image));
	        Image1.setBounds(78, 286, 234, 140);
	        add(Image1);
	        
	        image2 = new JLabel("");
	        //image2.setIcon(new ImageIcon(SachMuon.class.getResource("/Image/Picture4.png")));
	        ImageIcon icon = new ImageIcon(PhieuMuonFrame.class.getResource("/Image/Picture4.png"));
	        Image image1 = icon.getImage();
	        
	        Image newImg = image1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	        icon = new ImageIcon(newImg);
	        image2.setIcon(icon);
	        image2.setBounds(356, 268, 136, 189);
	        add(image2);
	        
	        image3 = new JLabel("");
	        ImageIcon icon1 = new ImageIcon(PhieuMuonFrame.class.getResource("/Image/Picture3.png"));
	        Image image2 = icon1.getImage();
	        
	        Image newImg1 = image2.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	        icon1 = new ImageIcon(newImg1);
	        image3.setIcon(icon1);
	        
	        image3.setBounds(597, 268, 136, 173);
	        add(image3);
	        
	        updateTableData(GUI_LOGIN.maDocGia);
	        
	        searchField.getDocument().addDocumentListener(new DocumentListener() {
	            public void insertUpdate(DocumentEvent e) {
	                search(searchField.getText());
	            }
	            public void removeUpdate(DocumentEvent e) {
	                search(searchField.getText());
	            }
	            public void changedUpdate(DocumentEvent e) {
	                search(searchField.getText());
	            }
	            private void search(String str) {
	                if (str.length() == 0) {
	                    sorter.setRowFilter(null);
	                } else {
	                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str, 3)); // 2 là chỉ số cột TenSach
	                }
	            }
	        });
	       
	    
	}
	  
    private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
//    private void updateTableData(String maDocGia) {
//        try {
//            PhieuMuonDAO dao = new PhieuMuonDAO(Database.Getconnect());
//            ResultSet rs = dao.getPhieuMuonDetail(maDocGia);
//            DefaultTableModel model = buildTableModel(rs);
//            table.setModel(model);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    
    private void updateTableData(String maDocGia) {
        try {
        	PhieuMuonDAO dao = new PhieuMuonDAO(Database.Getconnect());
            ResultSet rs = dao.getPhieuMuonDetail(maDocGia);
            DefaultTableModel model = buildTableModel(rs);
            table.setModel(model);
            sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
