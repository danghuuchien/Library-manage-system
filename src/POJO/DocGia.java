package POJO;
import java.util.Date;

public class DocGia {
	private String maDocGia;
    private String tenDangNhap;
    private String hoTen;
    private String gioiTinh;
    private Date namSinh;
    private String diaChi;

    public DocGia() {
    }

    public DocGia(String maDocGia, String tenDangNhap, String hoTen, String gioiTinh, Date namSinh, String diaChi) {
        this.maDocGia = maDocGia;
        this.tenDangNhap = tenDangNhap;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
