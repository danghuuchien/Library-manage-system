package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import POJO.PhieuTra;
import POJO.PhieuMuon;
import POJO.Sach;
public class PhieuMuonDAO {
	private Connection conn;

    
    public PhieuMuonDAO(Connection conn) {
        this.conn = conn;
    }

    public ResultSet getPhieuMuonDetail(String maDocGia) throws SQLException {
        String sql = "SELECT PM.MaPhieu, PM.MaDocGia, PM.MaSach,S.TenSach, PM.NgayMuon, PM.NgayPhaiTra,  S.TacGia, S.TheLoai " +
                     "FROM PHIEUMUON PM " +
                     "JOIN SACH S ON PM.MaSach = S.MaSach " +
                     "LEFT JOIN PHIEUTRA PT ON PM.MaPhieu = PT.MaPhieu " +
                     "WHERE PM.MaDocGia = ? AND PT.NgayTra IS NULL";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, maDocGia);
        return pstmt.executeQuery();
    }
}
