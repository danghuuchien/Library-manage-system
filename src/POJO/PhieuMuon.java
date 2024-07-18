package POJO;
import java.util.Date;
import POJO.PhieuMuon;
public class PhieuMuon {
	private String maPhieu;
    private String maDocGia;
    private String maSach;
    private Date ngayMuon;
    private Date ngayPhaiTra;

    public PhieuMuon() {
    }

    public PhieuMuon(String maPhieu, String maDocGia, String maSach, Date ngayMuon, Date ngayPhaiTra) {
        this.maPhieu = maPhieu;
        this.maDocGia = maDocGia;
        this.maSach = maSach;
        this.ngayMuon = ngayMuon;
        this.ngayPhaiTra = ngayPhaiTra;
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

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayPhaiTra() {
        return ngayPhaiTra;
    }

    public void setNgayPhaiTra(Date ngayPhaiTra) {
        this.ngayPhaiTra = ngayPhaiTra;
    }
}
