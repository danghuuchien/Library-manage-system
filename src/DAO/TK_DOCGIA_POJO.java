package DAO;

import java.util.Date;

public class TK_DOCGIA_POJO {
	public String getMaDocGia() {
		return MaDocGia;
	}
	public void setMaDocGia(String maDocGia) {
		MaDocGia = maDocGia;
	}
	public String getTenDangNhap() {
		return TenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		TenDangNhap = tenDangNhap;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public Date getNamSinh() {
		return NamSinh;
	}
	public void setNamSinh(Date namSinh) {
		NamSinh = namSinh;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	String MaDocGia;
	String TenDangNhap;
	String HoTen;
	String GioiTinh;
	Date NamSinh;
	String DiaChi;
}
