package TimKiem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.util.List;
import java.util.Optional;

import POJO.Sach;
import DAO.Connect;
import DAO.TimKiemSachPOJO;
import java.awt.*;
import java.sql.*;
import java.util.concurrent.CompletableFuture;
import com.assemblyai.api.AssemblyAI;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.assemblyai.api.resources.transcripts.types.*;







public class TimKiemSach extends JPanel {
    private JTextField txtTim;
    private JRadioButton rdMS;
    private JRadioButton rdTS;
    private JRadioButton rdTL;
    private JRadioButton rdTG;
    private JRadioButton rdTT;
    private JRadioButton rdNXB;
    private JTable table;
	private TimKiemSachPOJO timKiemSachPOJO;
	private AssemblyAI assemblyAI; 
	

    public TimKiemSach() {
        setBounds(100, 100, 825, 497);
        setLayout(null);

        JLabel lbTim = new JLabel("Nhập Dữ Liệu Cần Tìm:");
        lbTim.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lbTim.setBounds(118, 24, 184, 36);
        add(lbTim);

        txtTim = new JTextField();
        txtTim.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtTim.setBounds(312, 22, 305, 42);
        add(txtTim);

       

        JPanel panel = new JPanel();
        panel.setBackground(new Color(225, 240, 255));
        panel.setForeground(new Color(0, 204, 255));
        panel.setToolTipText("Tìm Kiếm Theo:");
        panel.setBorder((Border) new TitledBorder(new MatteBorder(25, 1, 1, 1, (Color) new Color(172, 214, 255)), "Tìm Kiếm Theo", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(284, 79, 316, 151);
        add(panel);
        panel.setLayout(null);

        
        ButtonGroup buttonGroup = new ButtonGroup();

        rdMS = new JRadioButton("Mã Sách");
        rdMS.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdMS.setBackground(new Color(221, 238, 255));
        rdMS.setBounds(41, 35, 103, 21);
        panel.add(rdMS);
        buttonGroup.add(rdMS);

        rdTS = new JRadioButton("Tên Sách");
        rdTS.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdTS.setBackground(new Color(221, 238, 255));
        rdTS.setBounds(41, 75, 103, 21);
        panel.add(rdTS);
        buttonGroup.add(rdTS);

        rdTL = new JRadioButton("Thể Loại");
        rdTL.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdTL.setBackground(new Color(221, 238, 255));
        rdTL.setBounds(41, 113, 103, 21);
        panel.add(rdTL);
        buttonGroup.add(rdTL);

        rdTG = new JRadioButton("Tác Giả");
        rdTG.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdTG.setBackground(new Color(221, 238, 255));
        rdTG.setBounds(180, 36, 103, 21);
        panel.add(rdTG);
        buttonGroup.add(rdTG);

        rdTT = new JRadioButton("Tình Trạng");
        rdTT.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdTT.setBackground(new Color(221, 238, 255));
        rdTT.setBounds(180, 76, 103, 21);
        panel.add(rdTT);
        buttonGroup.add(rdTT);

        rdNXB = new JRadioButton("Nhà Xuất Bản");
        rdNXB.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdNXB.setBackground(new Color(221, 238, 255));
        rdNXB.setBounds(180, 114, 130, 21);
        panel.add(rdNXB);
        buttonGroup.add(rdNXB);

        
        table = new JTable();
        JScrollPane jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(76, 241, 681, 189);
        add(jScrollPane1);
        
        assemblyAI = AssemblyAI.builder()
                .apiKey("6b2d8c25405d42f19904a02fd30157e4")
                .build();
        
        JButton btnGhiAm = new JButton("Ghi âm");
        btnGhiAm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  String audioUrl = "https://example.com/path/to/recorded-audio.wav"; 
        		  convertSpeechToText(audioUrl);
        	}
        });
        btnGhiAm.setBounds(633, 33, 89, 23);
        add(btnGhiAm);
        
        
        
        
        
        Connection connection = Connect.Getconnect();
        timKiemSachPOJO = new TimKiemSachPOJO(connection);
        displayData();
        
        
        txtTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txtTim.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txtTim.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txtTim.getText().trim());
            }
        });

        
        
      
    }
    private void displayData() {
    	//Connection connection = Connect.Getconnect();
    	 //timKiemSachPOJO = new TimKiemSachPOJO(connection);
        List<Sach> danhSachSach = timKiemSachPOJO.layDanhSachSach();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 
        String[] columnNames ={"Mã Sách", "Tên Sách", "Tác Giả", "Thể Loại", "Nhà Xuất Bản", "Tình Trạng"};
        model.setColumnIdentifiers(columnNames);

        for (Sach sach : danhSachSach) {
            Object[] rowData = {
                    sach.getMaSach(),
                    sach.getTenSach(),
                    sach.getTacGia(),
                    sach.getTheLoai(),
                    sach.getNhaXuatBan(),
                    sach.getTinhTrang()
            };
            model.addRow(rowData);
        }
    }
    private void search(String str) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        if (str.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            int selectedColumn = getSelectedColumn();
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str, selectedColumn));
        }
    }

    private int getSelectedColumn() {
        if (rdMS.isSelected()) {
            return 0; 
        } else if (rdTS.isSelected()) {
            return 1; // Tên Sách
        } else if (rdTL.isSelected()) {
            return 3; // Thể Loại
        } else if (rdTG.isSelected()) {
            return 2; // Tác Giả
        } else if (rdNXB.isSelected()) {
            return 4; // Nhà Xuất Bản
        } else if (rdTT.isSelected()) {
            return 5; // Tình Trạng
        }
        return -1; 
    }
    private Optional<String> convertSpeechToText(String audioUrl) {
        try {
            Transcript transcript = ((Object) assemblyAI).transcribeUrl(audioUrl);
            return transcript.getText();
        } catch (Exception e) {
            e.printStackTrace();
            //return "Error during speech-to-text conversion.";
        }
    }
   
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TimKiemSach frame = new TimKiemSach();
            frame.setVisible(true);
        });
    }
}
