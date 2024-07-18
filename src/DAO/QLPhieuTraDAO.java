package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QLPhieuTraDAO {
	   private Connection conn;

	    public QLPhieuTraDAO(Connection conn) {
	        this.conn = conn;
	    }
	    public ResultSet getPhieuTraData() throws SQLException {
	        String sql = "SELECT PM.MaPhieu, PM.MaDocGia, DG.HoTen AS TenDocGia, S.MaSach, S.TenSach, PM.NgayPhaiTra, PT.NgayTra, PT.TrangThai " +
	                     "FROM PHIEUMUON PM " +
	                     "JOIN DOCGIA DG ON PM.MaDocGia = DG.MaDocGia " +
	                     "JOIN SACH S ON PM.MaSach = S.MaSach " +
	                     "LEFT JOIN PHIEUTRA PT ON PM.MaPhieu = PT.MaPhieu " +
	                     "ORDER BY PM.NgayPhaiTra";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        return pstmt.executeQuery();
	    }
	    public void capNhatNgayTra(String maPhieu, Date ngayTra) throws SQLException {
	       
	        java.sql.Date sqlNgayTra = new java.sql.Date(ngayTra.getTime());	     
	        String sql = "UPDATE PHIEUTRA SET NgayTra = ? WHERE MaPhieu = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setDate(1, sqlNgayTra);
	        pstmt.setString(2, maPhieu);
	        pstmt.executeUpdate();
	    }
	    public void xoaNgayTra(String maPhieu) throws SQLException {
	        String sql = "UPDATE PHIEUTRA SET NgayTra = NULL WHERE MaPhieu = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, maPhieu);
	        pstmt.executeUpdate();
	    }
	    //SEARCH
	    public ResultSet timKiemTheoMaPhieu(String maPhieu) throws SQLException {
	        String sql = "SELECT PM.MaPhieu, PM.MaDocGia, DG.HoTen AS TenDocGia, S.MaSach, S.TenSach, PM.NgayPhaiTra, PT.NgayTra, PT.TrangThai " +
	                     "FROM PHIEUMUON PM " +
	                     "JOIN DOCGIA DG ON PM.MaDocGia = DG.MaDocGia " +
	                     "JOIN SACH S ON PM.MaSach = S.MaSach " +
	                     "LEFT JOIN PHIEUTRA PT ON PM.MaPhieu = PT.MaPhieu " +
	                     "WHERE PM.MaPhieu LIKE ? " +
	                     "ORDER BY PM.NgayPhaiTra";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + maPhieu + "%");
	        return pstmt.executeQuery();
	    }

	    public ResultSet timKiemTheoMaDocGia(String maDocGia) throws SQLException {
	        String sql = "SELECT PM.MaPhieu, PM.MaDocGia, DG.HoTen AS TenDocGia, S.MaSach, S.TenSach, PM.NgayPhaiTra, PT.NgayTra, PT.TrangThai " +
	                     "FROM PHIEUMUON PM " +
	                     "JOIN DOCGIA DG ON PM.MaDocGia = DG.MaDocGia " +
	                     "JOIN SACH S ON PM.MaSach = S.MaSach " +
	                     "LEFT JOIN PHIEUTRA PT ON PM.MaPhieu = PT.MaPhieu " +
	                     "WHERE DG.MaDocGia LIKE ? " +
	                     "ORDER BY PM.NgayPhaiTra";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + maDocGia + "%");
	        return pstmt.executeQuery();
	    }

	    public ResultSet timKiemTheoNgayTra(String ngayTra) throws SQLException {
	        String sql = "SELECT PM.MaPhieu, PM.MaDocGia, DG.HoTen AS TenDocGia, S.MaSach, S.TenSach, PM.NgayPhaiTra, PT.NgayTra, PT.TrangThai " +
	                     "FROM PHIEUMUON PM " +
	                     "JOIN DOCGIA DG ON PM.MaDocGia = DG.MaDocGia " +
	                     "JOIN SACH S ON PM.MaSach = S.MaSach " +
	                     "LEFT JOIN PHIEUTRA PT ON PM.MaPhieu = PT.MaPhieu " +
	                     "WHERE PT.NgayTra LIKE ? " +
	                     "ORDER BY PM.NgayPhaiTra";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, "%" + ngayTra + "%");
	        return pstmt.executeQuery();
	    }
	    
	    
}
