package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
//import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import MyPack.Database;
import MyPack.GUI_LOGIN;
import DAO.LichSuMuonDAO;
public class LichSuMuon extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchField;
	private JTable table;
	private TableRowSorter<DefaultTableModel> sorter;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
	               //
	                if (Database.Connect()) {
	                    System.out.println("Kết nối tới cơ sở dữ liệu thành công.");
	                    LichSuMuon frame = new LichSuMuon();
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
	public LichSuMuon() {
		setBounds(100, 100, 1008, 602);

		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tìm tên sách");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(172, 57, 90, 14);
		add(lblNewLabel);
		
		searchField = new JTextField();
		searchField.setBounds(272, 51, 316, 28);
		add(searchField);
		searchField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 124, 805, 104);
		add(scrollPane);
	     
		

		table = new JTable();
		scrollPane.setViewportView(table);
		//căn giữa cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }
        

        
        JLabel image1 = new JLabel("");
        
        ImageIcon originalIcon = new ImageIcon(PhieuMuonFrame.class.getResource("/Image/Picture10.png"));
        Image image = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        image1.setIcon(new ImageIcon(image));
        
        image1.setBounds(60, 276, 146, 137);
        add(image1);
        
        JLabel image2 = new JLabel("");
        //image2.setIcon(new ImageIcon(LichSuMuon.class.getResource("/Image/Picture11.png")));
        ImageIcon originalIco = new ImageIcon(LichSuMuon.class.getResource("/Image/Picture11.png"));
        Image originalImage = originalIco.getImage();
        Image resizedImage = originalImage.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        image2.setIcon(resizedIcon);
        image2.setBounds(309, 286, 140, 127);
        add(image2);
        
        JLabel image3 = new JLabel("");
        
        ImageIcon originalIcon7 = new ImageIcon(LichSuMuon.class.getResource("/Image/Picture7.png"));
     Image originalImage7 = originalIcon7.getImage();
     Image resizedImage7 = originalImage7.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
     ImageIcon resizedIcon7 = new ImageIcon(resizedImage7);
     image3.setIcon(resizedIcon7);
        image3.setBounds(610, 276, 194, 127);
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
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str, 1)); // 2 là chỉ số cột TenSach
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

	// Phương thức để cập nhật dữ liệu bảng
//	private void updateTableData(String maDocGia) {
//	    try {
//	        LichSuMuonDAO dao = new LichSuMuonDAO(Database.Getconnect());
//	        ResultSet rs = dao.getLichSuMuon(maDocGia);
//	        DefaultTableModel model = buildTableModel(rs);
//	        table.setModel(model);
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	}
    private void updateTableData(String maDocGia) {
        try {
            LichSuMuonDAO dao = new LichSuMuonDAO(Database.Getconnect());
            ResultSet rs = dao.getLichSuMuon(maDocGia);
            DefaultTableModel model = buildTableModel(rs);
            table.setModel(model);
            sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}


