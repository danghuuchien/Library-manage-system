package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import POJO.PhieuMuon;
import POJO.PhieuTra;
import GUI.LichSuMuon;

public class LichSuMuonDAO {
	 private Connection conn; 
	    public LichSuMuonDAO(Connection conn) {
	        this.conn = conn;
	    }
	    public ResultSet getLichSuMuon(String maDocGia) throws SQLException {
	        String sql = "SELECT PM.MaPhieu, S.TenSach, S.TacGia, S.TheLoai, PM.NgayMuon, PM.NgayPhaiTra, PT.NgayTra, PT.TrangThai " +
	                     "FROM PHIEUMUON PM " +
	                     "JOIN SACH S ON PM.MaSach = S.MaSach " +
	                     "LEFT JOIN PHIEUTRA PT ON PM.MaPhieu = PT.MaPhieu " +
	                     "WHERE PM.MaDocGia = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, maDocGia);
	        return pstmt.executeQuery();
	    }
	
}
