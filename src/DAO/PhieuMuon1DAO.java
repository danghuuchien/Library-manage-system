package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import POJO.PhieuMuonPOJO;

public class PhieuMuon1DAO {
	private Connection connection;

    public PhieuMuon1DAO(Connection connection) {
        this.connection = connection;
    }

    public List<PhieuMuonPOJO> getAllPhieuMuon() {
        List<PhieuMuonPOJO> phieuMuonList = new ArrayList<>();
        String query = "SELECT * FROM PHIEUMUON";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PhieuMuonPOJO phieuMuon = new PhieuMuonPOJO();
                phieuMuon.setMaPhieu(resultSet.getString("MaPhieu"));
                phieuMuon.setMaDocGia(resultSet.getString("MaDocGia"));
                phieuMuon.setMaSach(resultSet.getString("MaSach"));
                phieuMuon.setNgayMuon(resultSet.getDate("NgayMuon"));
                phieuMuon.setNgayPhaiTra(resultSet.getDate("NgayPhaiTra"));
                phieuMuonList.add(phieuMuon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phieuMuonList;
    }
    

    public boolean kiemTraMaPhieuTrung(String maPhieu) {
        String query = "SELECT COUNT(*) FROM PHIEUMUON WHERE MaPhieu = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maPhieu);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "Mã phiếu bị trùng!");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean kiemTraMaDocGiaTonTai(String maDocGia) {
        String query = "SELECT COUNT(*) FROM DOCGIA WHERE MaDocGia = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maDocGia);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count == 0) {
                        JOptionPane.showMessageDialog(null, "Không có mã độc giả!");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean kiemTraMaSachTonTai(String maSach) {
        String query = "SELECT COUNT(*) FROM SACH WHERE MaSach = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maSach);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count == 0) {
                        JOptionPane.showMessageDialog(null, "Không tồn tại mã sách!");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean kiemTraNgayPhaiTraHopLe(Date ngayMuon, Date ngayPhaiTra) {
        if (ngayMuon == null || ngayPhaiTra == null) {
            JOptionPane.showMessageDialog(null, "Ngày mượn hoặc ngày phải trả không hợp lệ!");
            return false;
        }
        if (ngayPhaiTra.before(ngayMuon)) {
            JOptionPane.showMessageDialog(null, "Ngày phải trả phải sau ngày mượn!");
            return false;
        }
        return true;
    }
    	//themPhieuMuon
    	public boolean themPhieuMuon(PhieuMuonPOJO phieuMuon, java.sql.Date sqlNgayMuon, java.sql.Date sqlNgayPhaiTra) {
            String sql = "INSERT INTO PhieuMuon (maPhieu, maDocGia, maSach, ngayMuon, ngayPhaiTra) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, phieuMuon.getMaPhieu());
                stmt.setString(2, phieuMuon.getMaDocGia());
                stmt.setString(3, phieuMuon.getMaSach());
                stmt.setDate(4, sqlNgayMuon);
                stmt.setDate(5, sqlNgayPhaiTra);
                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    	
    	public boolean xoaPhieuMuon(String maPhieuMuon) {
            String sql = "DELETE FROM PHIEUMUON WHERE MaPhieu = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, maPhieuMuon);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    	
    	public boolean capNhatPhieuMuon(PhieuMuonPOJO phieuMuon) {
    		String sql = "UPDATE PHIEUMUON SET MaDocGia = ?, MaSach = ?, NgayMuon = ?, NgayPhaiTra = ? WHERE MaPhieu = ?";
    	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    	        preparedStatement.setString(1, phieuMuon.getMaDocGia());
    	        preparedStatement.setString(2, phieuMuon.getMaSach());
    	        preparedStatement.setDate(3, (Date) phieuMuon.getNgayMuon());
    	        preparedStatement.setDate(4, (Date) phieuMuon.getNgayPhaiTra());
    	        preparedStatement.setString(5, phieuMuon.getMaPhieu());

    	        int rowsAffected = preparedStatement.executeUpdate();
    	        if (rowsAffected > 0) {
    	            return true; 
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật phiếu mượn!");
    	    }
    	    return false; 
        }

    	


}
