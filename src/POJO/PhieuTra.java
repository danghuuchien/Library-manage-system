package POJO;
import java.util.Date;
public class PhieuTra {
	private String maPhieu;
    private String maDocGia;
    private String maSach;
    private Date ngayTra;
    private String trangThai;

    public PhieuTra() {
    }

    public PhieuTra(String maPhieu, String maDocGia, String maSach, Date ngayTra, String trangThai) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.maSach = maSach;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
