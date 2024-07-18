package TimKiem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import DAO.Connect;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.PhieuMuon1DAO;
import POJO.PhieuMuonPOJO;


public class PhieuMuon extends JPanel {

    private static final long serialVersionUID = 1L;
	private static final String String = null;
    private JPanel contentPane;
    private JTextField txtTimKiem;
    private JTable table;
  //  private PhieuMuonDAO phieuMuonDAO;
    private JTextField txtMaPhieu;
    private JTextField txtMaDocGia;
    private JTextField txtMaSach;
    private JTextField txtNgayMuon;
    private JTextField txtNgayPhaiTra;
    private JRadioButton rdbtnMaPhieu;
    private JRadioButton rdbtnMaSach;
    private JRadioButton rdbtnNgayMuon;
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
                try {
                    PhieuMuon frame = new PhieuMuon();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    
    public PhieuMuon() {
       	

        setBounds(100, 100, 862, 567);
        contentPane = new JPanel();
        setBorder(new EmptyBorder(5, 5, 5, 5));

        setLayout(null);

        JLabel lblTmKim = new JLabel("Tìm Kiếm:");
        lblTmKim.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblTmKim.setBounds(484, 25, 80, 36);
        add(lblTmKim);

        txtTimKiem = new JTextField();
        txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtTimKiem.setBounds(565, 23, 190, 42);
        add(txtTimKiem);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setToolTipText("Tìm Kiếm Theo:");
        panel.setForeground(new Color(0, 204, 255));
        panel.setBorder((Border) new TitledBorder(new MatteBorder(25, 1, 1, 1, (Color) new Color(172, 214, 255)), "Tìm Kiếm Theo", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBackground(new Color(225, 240, 255));
        panel.setBounds(494, 72, 295, 151);
        add(panel);
        
        ButtonGroup buttonGroup = new ButtonGroup();

        rdbtnMaPhieu = new JRadioButton("Mã Phiếu");
        rdbtnMaPhieu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdbtnMaPhieu.setBackground(new Color(221, 238, 255));
        rdbtnMaPhieu.setBounds(89, 38, 103, 21);
        panel.add(rdbtnMaPhieu);
        rdbtnMaPhieu.setSelected(true);
        buttonGroup.add(rdbtnMaPhieu);

        rdbtnMaSach = new JRadioButton("Mã Sách");
        rdbtnMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdbtnMaSach.setBackground(new Color(221, 238, 255));
        rdbtnMaSach.setBounds(89, 76, 103, 21);
        panel.add(rdbtnMaSach);
        buttonGroup.add(rdbtnMaSach);

        rdbtnNgayMuon = new JRadioButton("Ngày Mượn");
        rdbtnNgayMuon.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdbtnNgayMuon.setBackground(new Color(221, 238, 255));
        rdbtnNgayMuon.setBounds(89, 112, 103, 21);
        panel.add(rdbtnNgayMuon);
        buttonGroup.add(rdbtnNgayMuon);

  

       
        

        table = new JTable();
     
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(table);
        jScrollPane1.setBounds(10, 288, 828, 189);
        add(jScrollPane1);
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	
                
            }
        });
        
        
        JLabel lblMP = new JLabel("Mã Phiếu:");
        lblMP.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblMP.setBounds(10, 10, 80, 36);
        add(lblMP);

        txtMaPhieu = new JTextField();
        txtMaPhieu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtMaPhieu.setBounds(140, 16, 190, 26);
        add(txtMaPhieu);
        txtMaPhieu.setEnabled(false);

        JLabel lblMĐG = new JLabel("Mã Độc Giả:");
        lblMĐG.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblMĐG.setBounds(10, 47, 120, 36);
        add(lblMĐG);

        txtMaDocGia = new JTextField();
        txtMaDocGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtMaDocGia.setBounds(140, 53, 190, 26);
        add(txtMaDocGia);

        JLabel lblMS = new JLabel("Mã Sách:");
        lblMS.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblMS.setBounds(10, 87, 80, 36);
        add(lblMS);

        txtMaSach = new JTextField();
        txtMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtMaSach.setBounds(140, 93, 190, 26);
        add(txtMaSach);

        JLabel lblNM = new JLabel("Ngày Mượn:");
        lblNM.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblNM.setBounds(10, 127, 97, 36);
        add(lblNM);

        txtNgayMuon = new JTextField();
        txtNgayMuon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtNgayMuon.setBounds(140, 133, 190, 26);
        add(txtNgayMuon);

        JLabel lblNPT = new JLabel("Ngày phải trả:");
        lblNPT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblNPT.setBounds(10, 167, 120, 36);
        add(lblNPT);

        txtNgayPhaiTra = new JTextField();
        txtNgayPhaiTra.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtNgayPhaiTra.setBounds(140, 173, 190, 26);
        add(txtNgayPhaiTra);
        
        PhieuMuon1DAO phieuMuonDAO = new PhieuMuon1DAO(Connect.Getconnect());
        
        JButton btn_Them = new JButton("Thêm");
        btn_Them.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btn_Them.setBounds(20, 224, 87, 41);
        add(btn_Them);
        btn_Them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String maPhieu = txtMaPhieu.getText();
                String maDocGia = txtMaDocGia.getText();
                String maSach = txtMaSach.getText();
                String ngayMuonStr = txtNgayMuon.getText();
                String ngayPhaiTraStr = txtNgayPhaiTra.getText();

                if (maPhieu.isEmpty() || maDocGia.isEmpty() || maSach.isEmpty() || ngayMuonStr.isEmpty() || ngayPhaiTraStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

                if (phieuMuonDAO.kiemTraMaPhieuTrung(maPhieu)) {
                    return;
                }

                if (!phieuMuonDAO.kiemTraMaDocGiaTonTai(maDocGia)) {
                    return;
                }

                if (!phieuMuonDAO.kiemTraMaSachTonTai(maSach)) {
                    return;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    java.util.Date utilNgayMuon = dateFormat.parse(ngayMuonStr);
                    java.util.Date utilNgayPhaiTra = dateFormat.parse(ngayPhaiTraStr);

                    java.sql.Date sqlNgayMuon = new java.sql.Date(utilNgayMuon.getTime());
                    java.sql.Date sqlNgayPhaiTra = new java.sql.Date(utilNgayPhaiTra.getTime());

                    if (!phieuMuonDAO.kiemTraNgayPhaiTraHopLe(sqlNgayMuon, sqlNgayPhaiTra)) {
                        return;
                    }

                    PhieuMuonPOJO phieuMuon = new PhieuMuonPOJO(maPhieu, maDocGia, maSach, utilNgayMuon, utilNgayPhaiTra);
                    boolean success = phieuMuonDAO.themPhieuMuon(phieuMuon, sqlNgayMuon, sqlNgayPhaiTra);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công!");
                        // Có thể làm mới dữ liệu bảng ở đây nếu muốn
                        List<PhieuMuonPOJO> phieuMuonList = phieuMuonDAO.getAllPhieuMuon();
                        populateTableWithData(table, phieuMuonList);
                        txtMaPhieu.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm phiếu mượn!");
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ!");
                }
                }
         });

        JButton btn_Xoa = new JButton("Xóa");
        btn_Xoa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String maPhieuMuon = txtMaPhieu.getText(); // Lấy mã phiếu mượn từ JTextField

        		// Kiểm tra nếu mã phiếu mượn trống
        		if (maPhieuMuon.isEmpty()) {
        		    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn để xóa!");
        		    return; // Thoát khỏi phương thức
        		}

        		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu mượn?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        		if (confirm == JOptionPane.YES_OPTION) {
        		    boolean xoaThanhCong = phieuMuonDAO.xoaPhieuMuon(maPhieuMuon);

        		    if (xoaThanhCong) {
        		        JOptionPane.showMessageDialog(null, "Xóa phiếu mượn thành công!");
        		        List<PhieuMuonPOJO> phieuMuonList = phieuMuonDAO.getAllPhieuMuon();
        		        populateTableWithData(table, phieuMuonList);
        		        txtMaPhieu.setText("");
        		        txtMaSach.setText("");
        		        txtNgayPhaiTra.setText("");
        		        txtNgayMuon.setText("");
        		        txtMaDocGia.setText("");
        		    } else {
        		        JOptionPane.showMessageDialog(null, "Lỗi khi xóa phiếu mượn!");
        		    }
        		}
        		
        	}
        });
        btn_Xoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btn_Xoa.setBounds(119, 224, 83, 41);
        add(btn_Xoa);

        JButton btn_Sua = new JButton("Sửa");
        btn_Sua.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btn_Sua.setBounds(212, 224, 80, 41);
        add(btn_Sua);
        btn_Sua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String maPhieu = txtMaPhieu.getText();
                String maDocGia = txtMaDocGia.getText();
                String maSach = txtMaSach.getText();
                String ngayMuonStr = txtNgayMuon.getText();
                String ngayPhaiTraStr = txtNgayPhaiTra.getText();

                if (maPhieu.isEmpty() || maDocGia.isEmpty() || maSach.isEmpty() || ngayMuonStr.isEmpty() || ngayPhaiTraStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }

//                if (phieuMuonDAO.kiemTraMaPhieuTrung(maPhieu)) {
//                    return;
//                }

                if (!phieuMuonDAO.kiemTraMaDocGiaTonTai(maDocGia)) {
                    return;
                }

                if (!phieuMuonDAO.kiemTraMaSachTonTai(maSach)) {
                    return;
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    java.util.Date utilNgayMuon = dateFormat.parse(ngayMuonStr);
                    java.util.Date utilNgayPhaiTra = dateFormat.parse(ngayPhaiTraStr);

                    java.sql.Date sqlNgayMuon = new java.sql.Date(utilNgayMuon.getTime());
                    java.sql.Date sqlNgayPhaiTra = new java.sql.Date(utilNgayPhaiTra.getTime());

                    if (!phieuMuonDAO.kiemTraNgayPhaiTraHopLe(sqlNgayMuon, sqlNgayPhaiTra)) {
                        return;
                    }
                   
                    PhieuMuonPOJO phieuMuon = new PhieuMuonPOJO(maPhieu, maDocGia, maSach, sqlNgayMuon, sqlNgayPhaiTra);
                    boolean capNhatThanhCong = phieuMuonDAO.capNhatPhieuMuon(phieuMuon);
                    if (capNhatThanhCong) {
                        JOptionPane.showMessageDialog(null, "Cập nhật phiếu mượn thành công!");
                        // Cập nhật lại dữ liệu trong bảng sau khi cập nhật thành công
                        List<PhieuMuonPOJO> phieuMuonList = phieuMuonDAO.getAllPhieuMuon();
                        populateTableWithData(table, phieuMuonList);
                        txtMaPhieu.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật phiếu mượn!");
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi chuyển đổi ngày!");
                }
            }
        });
      
        List<PhieuMuonPOJO> phieuMuonList = phieuMuonDAO.getAllPhieuMuon();
        populateTableWithData(table, phieuMuonList);
        
        JButton btn_Them_1 = new JButton("Làm mới");
        btn_Them_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtMaPhieu.setEnabled(true);
        		txtMaPhieu.setText("");
        		txtMaSach.setText("");
        		txtNgayPhaiTra.setText("");
        		txtNgayMuon.setText("");
        		//txtMaDocGia.setText(("");
        		txtMaDocGia.setText("");
        	}
        });
        btn_Them_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btn_Them_1.setBounds(319, 224, 114, 41);
        add(btn_Them_1);
        
   
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              
                int row = table.getSelectedRow();
                
                
                if (row >= 0) {
                    String maPhieu = table.getValueAt(row, 0).toString();
                    String maDocGia = table.getValueAt(row, 1).toString();
                    String maSach = table.getValueAt(row, 2).toString();
                    Date ngayMuon = (Date) table.getValueAt(row, 3);
                    Date ngayPhaiTra = (Date) table.getValueAt(row, 4);

                    txtMaPhieu.setText(maPhieu);
                    txtMaDocGia.setText(maDocGia);
                    txtMaSach.setText(maSach);
                    txtNgayMuon.setText(ngayMuon.toString());
                    txtNgayPhaiTra.setText(ngayPhaiTra.toString());
                    txtMaPhieu.setEnabled(false);
                }
            }
        });

        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txtTimKiem.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txtTimKiem.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txtTimKiem.getText());
            }
        });

        
        
    }
    public static void populateTableWithData(JTable table, List<PhieuMuonPOJO> phieuMuonList) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu");
        tableModel.addColumn("Mã độc giả");
        tableModel.addColumn("Mã sách");
        tableModel.addColumn("Ngày mượn");
        tableModel.addColumn("Ngày phải trả");

        for (PhieuMuonPOJO phieuMuon : phieuMuonList) {
            tableModel.addRow(new Object[]{
                    phieuMuon.getMaPhieu(),
                    phieuMuon.getMaDocGia(),
                    phieuMuon.getMaSach(),
                    phieuMuon.getNgayMuon(),
                    phieuMuon.getNgayPhaiTra()
            });
        }

        table.setModel(tableModel);
    }
    private void search(String str) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        if (str.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            int selectedColumn = getSelectedColumn();
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str, selectedColumn));
        }
    }

    private int getSelectedColumn() {
        if (rdbtnMaPhieu.isSelected()) {
            return 0; 
        } else if (rdbtnMaSach.isSelected()) {
            return 2; 
        } else if (rdbtnNgayMuon.isSelected()) {
            return 3; 
        }
        return 1;
    }
   
}


