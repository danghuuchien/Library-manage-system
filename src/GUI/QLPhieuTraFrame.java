package GUI;

import java.awt.EventQueue;
import MyPack.Database;
import DAO.QLPhieuTraDAO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.util.Vector;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.text.StyledDocument;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.net.URL;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class QLPhieuTraFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchField;
	private TableRowSorter<DefaultTableModel> sorter;
	
	
	private JTable table;
	private JRadioButton rdbMaPhieu;
    private JRadioButton rdbMaDocGia;
    private JRadioButton rdbNgayTra;
    private int currentSearchColumn;
   // private int currentSearchColumn;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 if (Database.Connect()) {
		                    System.out.println("Kết nối tới cơ sở dữ liệu thành công.");
		                    QLPhieuTraFrame frame = new QLPhieuTraFrame();
		                    frame.setVisible(true);
		                } else {
		                    System.out.println("Không thể kết nối tới cơ sở dữ liệu.");
		                }
					
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QLPhieuTraFrame() {
		setBounds(100, 100, 1008, 602);
		contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Phiếu");
		lblNewLabel.setBounds(113, 51, 82, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Độc Giả");
		lblNewLabel_1.setBounds(113, 92, 94, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mã Sách");
		lblNewLabel_2.setBounds(113, 137, 49, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ngày Phải Trả");
		lblNewLabel_3.setBounds(113, 176, 94, 14);
		add(lblNewLabel_3);	
		
		JTextPane txtMaPhieu = new JTextPane();
		txtMaPhieu.setEnabled(false);
		txtMaPhieu.setBounds(205, 51, 129, 20);
		add(txtMaPhieu);
		
		JTextPane txtMaDocGia = new JTextPane();
		txtMaDocGia.setEnabled(false);
		txtMaDocGia.setBounds(205, 86, 129, 20);
		add(txtMaDocGia);
		
		JTextPane txtMaSach = new JTextPane();
		txtMaSach.setEnabled(false);
		txtMaSach.setBounds(205, 137, 129, 20);
		add(txtMaSach);
		
		JTextPane txtTenSach = new JTextPane();
		txtTenSach.setEnabled(false);
		txtTenSach.setBounds(486, 131, 165, 20);
		add(txtTenSach);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(47, 229, 745, 179);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	
		
	
		
		rdbMaPhieu = new JRadioButton("Mã phiếu");
		rdbMaPhieu.setBounds(420, 88, 111, 23);
		add(rdbMaPhieu);
		rdbMaPhieu.setSelected(true);
				
		rdbMaDocGia = new JRadioButton("Mã độc giả");
		rdbMaDocGia.setBounds(540, 88, 111, 23);
		add(rdbMaDocGia);
		
		rdbNgayTra = new JRadioButton("Ngày trả");
		rdbNgayTra.setBounds(655, 88, 89, 23);
		add(rdbNgayTra);
		
		java.util.Date currentDate = new java.util.Date();
		
		JDateChooser txtTra = new JDateChooser();
		txtTra.setDate(currentDate);
		txtTra.setDateFormatString("dd-MM-yyyy");
		txtTra.setBounds(804, 27, 100, 20);
		add(txtTra);
		txtTra.setVisible(false);
		
		JButton btnTraSach = new JButton("Trả sách");

		btnTraSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            String maPhieu = table.getValueAt(selectedRow, 0).toString();
		            if (table.getValueAt(selectedRow, 6) != null) {
		                JOptionPane.showMessageDialog(null, "Độc giả đã trả sách rồi.");
		            } else {
		                // Cập nhật ngày trả sách
		                java.util.Date currentDate = new java.util.Date();
		                java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
		                try {
		                    QLPhieuTraDAO phieuTraDAO = new QLPhieuTraDAO(Database.Getconnect());
		                    phieuTraDAO.capNhatNgayTra(maPhieu, sqlDate);
		                    JOptionPane.showMessageDialog(null, "Trả sách thành công!");
		                    updateTableData();
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật ngày trả.");
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu mượn từ bảng.");
		        }
				
				
		    }
		});
		
		 Image img1 = new ImageIcon(QLPhieuTraFrame.class.getResource("/Image/Picture2.png")).getImage();
	     Image newImg1 = img1.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
	     ImageIcon iconXoa1= new ImageIcon(newImg1);
	     btnTraSach.setIcon(iconXoa1);
	     
		btnTraSach.setBounds(322, 419, 129, 23);

		add(btnTraSach);
		
		JDateChooser txtNgayPhaiTra = new JDateChooser();
		txtNgayPhaiTra.setDateFormatString("dd-MM-yyyy"); 
		txtNgayPhaiTra.setBounds(205, 176, 129, 20);
		txtNgayPhaiTra.setEnabled(false);
		add(txtNgayPhaiTra);
		
		JButton btnXoa = new JButton("Xóa");
		  
	    btnXoa.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       		int selectedRow = table.getSelectedRow();
	            if (selectedRow >= 0) {
	                String maPhieu = table.getValueAt(selectedRow, 0).toString();
	                try {
	                    QLPhieuTraDAO phieuTraDAO = new QLPhieuTraDAO(Database.Getconnect());
	                    phieuTraDAO.xoaNgayTra(maPhieu);
	                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
	                    updateTableData();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Lỗi khi xóa NgayTra.");
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Vui lòng chọn một phiếu mượn từ bảng.");
	            }
	       	}
	               
	            
	        });
	        
	     Image img = new ImageIcon(QLPhieuTraFrame.class.getResource("/Image/Picture1.png")).getImage();
	     Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	     ImageIcon iconXoa = new ImageIcon(newImg);
	     btnXoa.setIcon(iconXoa);

	        
	        btnXoa.setBounds(148, 419, 89, 23);
	        add(btnXoa);
		
	        
		JLabel lblNewLabel_3_1 = new JLabel("Ngày Trả");
		lblNewLabel_3_1.setBounds(433, 176, 63, 14);
		add(lblNewLabel_3_1);
				
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ti\u0300m ki\u00EA\u0301m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Ti\u0300m ki\u00EA\u0301m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(389, 13, 363, 107);
		add(panel);
		panel.setLayout(null);
		
		searchField = new JTextField();
		searchField.setBounds(40, 27, 248, 20);
		panel.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tên Sách");
		lblNewLabel_2_1.setBounds(389, 137, 62, 14);
		add(lblNewLabel_2_1);
		
		JDateChooser txtNow = new JDateChooser();
		txtNow.setDateFormatString("dd-MM-yyyy");
		txtNow.setBounds(522, 170, 129, 20);
		txtNow.setEnabled(false);
		add(txtNow);

        
     
       
        
        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(rdbMaPhieu);
        searchGroup.add(rdbMaDocGia);
        searchGroup.add(rdbNgayTra);
        
        JLabel image1 = new JLabel("");
     
        ImageIcon originalIcon = new ImageIcon(QLPhieuTraFrame.class.getResource("/Image/Picture5.png"));
        Image image = originalIcon.getImage().getScaledInstance(80, 90, Image.SCALE_SMOOTH);
        image1.setIcon(new ImageIcon(image));
        image1.setBounds(723, 419, 129, 79);
        add(image1);
        
        updateTableData();
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                  
                    txtMaPhieu.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                    txtMaDocGia.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtMaSach.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                    txtTenSach.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                    
                    String ngayPhaiTraStr = table.getValueAt(table.getSelectedRow(), 5).toString();
                    String[] parts = ngayPhaiTraStr.split(" ");
                    String ngayPhaiTraDate = parts[0]; 
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date ngayPhaiTra = dateFormat.parse(ngayPhaiTraDate);
                        txtNgayPhaiTra.setDate(ngayPhaiTra);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    
                    Object ngayTraObj = table.getValueAt(table.getSelectedRow(), 6);
                    if (ngayTraObj != null) {
                        String ngayTraStr = ngayTraObj.toString();
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date ngayTra = dateFormat.parse(ngayTraStr);
                            txtNow.setDate(ngayTra);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        txtNow.setDate(null); // Đặt giá trị null cho JDateChooser nếu cột là null
                    }
                     
                    

                   
                  
                }
            }
        });
        
        
        

        updateTableData();
      
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchField.getText());
            }
        });
        
        
    }
    private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        Vector<String> columnNames = new Vector<>();
        int columnCount = rs.getMetaData().getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(rs.getMetaData().getColumnName(column));
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

    private void updateTableData() {
        try {
            QLPhieuTraDAO dao = new QLPhieuTraDAO(Database.Getconnect());
            ResultSet rs = dao.getPhieuTraData();
            DefaultTableModel model = buildTableModel(rs);
            table.setModel(model);
            sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void search(String str) {
        if (str.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            int selectedColumn = getSelectedColumn();
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str, selectedColumn));
        }
    }

    private int getSelectedColumn() {
        if (rdbMaPhieu.isSelected()) {
            return 0; // Chỉ số cột cho MaPhieu
        } else if (rdbMaDocGia.isSelected()) {
            return 1; // Chỉ số cột cho MaDocGia
        } else if (rdbNgayTra.isSelected()) {
            return 6; // Chỉ số cột cho NgayTra
        }
       
        return 0;
    }
    
}
