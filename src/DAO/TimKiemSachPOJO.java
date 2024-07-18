package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import POJO.Sach;
public class TimKiemSachPOJO {
	 private Connection connection;

	    public TimKiemSachPOJO(Connection connection) {
	        this.connection = connection;
	    }

	    public List<Sach> layDanhSachSach() {
	        List<Sach> danhSachSach = new ArrayList<>();

	        String sql = "SELECT MaSach, TenSach, TacGia, TheLoai, NhaXuatBan, TinhTrang FROM Sach";

	        try (PreparedStatement ps = connection.prepareStatement(sql)) {
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                String maSach = rs.getString("MaSach");
	                String tenSach = rs.getString("TenSach");
	                String tacGia = rs.getString("TacGia");
	                String theLoai = rs.getString("TheLoai");
	                String nhaXuatBan = rs.getString("NhaXuatBan");
	                String tinhTrang = rs.getString("TinhTrang");

	                Sach sach = new Sach(maSach, tenSach, tacGia, theLoai, nhaXuatBan, 0, 0, tinhTrang, tinhTrang);
	                danhSachSach.add(sach);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return danhSachSach;
	    }
}
