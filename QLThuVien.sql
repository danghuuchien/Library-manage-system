CREATE TABLE DOCGIA
(
    MaDocGia NVARCHAR2(10),
    TenDangNhap NVARCHAR2(30),
    HoTen NVARCHAR2(30),
    GioiTinh NVARCHAR2(5),
    NamSinh DATE,
    DiaChi NVARCHAR2(100),
    PRIMARY KEY (MaDocGia)
);

CREATE TABLE SACH
(
    MaSach NVARCHAR2(10),
    TenSach NVARCHAR2(50),
    TacGia NVARCHAR2(30),
    TheLoai NVARCHAR2(30),
    NhaXuatBan NVARCHAR2(50),
    GiaSach NUMBER,
    SoLuong NUMBER,
    TinhTrang NVARCHAR2(10),
    Ghichu NVARCHAR2(255),
    PRIMARY KEY (MaSach)
);

CREATE TABLE PHIEUMUON
(
    MaPhieu NVARCHAR2(10),
    MaDocGia NVARCHAR2(10),
    MaSach NVARCHAR2(10),
    NgayMuon DATE,
    NgayPhaiTra DATE,
    PRIMARY KEY (MaPhieu),
    FOREIGN KEY (MaDocGia) REFERENCES DOCGIA (MaDocGia),
    FOREIGN KEY (MaSach) REFERENCES SACH (MaSach)
);

CREATE TABLE PHIEUTRA
(
    MaPhieu NVARCHAR2(10),
    MaDocGia NVARCHAR2(10),
    MaSach NVARCHAR2(10),
    NgayTra DATE,
    PRIMARY KEY (MaPhieu),
    FOREIGN KEY (MaDocGia) REFERENCES DOCGIA (MaDocGia),
    FOREIGN KEY (MaSach) REFERENCES SACH (MaSach)
);

CREATE TABLE ACCOUNT
(
    MaDocGia NVARCHAR2(10) PRIMARY KEY,
    TenDangNhap NVARCHAR2(30),
    MatKhau NVARCHAR2(30),
    Quyen NUMBER
);

INSERT INTO ACCOUNT VALUES ('15', 'Nguyễn Hữu Hoàng Hiếu', '123456', 1);
INSERT INTO ACCOUNT VALUES ('1', 'phamhaingoc1', '123456', 1);
INSERT INTO ACCOUNT VALUES ('2', 'phamhaingoc2', '123456', 1);
INSERT INTO ACCOUNT VALUES ('3', 'phamminhhoang', '123456', 1);
INSERT INTO ACCOUNT VALUES ('7', 'nguyenthanhhai', '123456', 1);
INSERT INTO ACCOUNT VALUES ('999', 'admin', '123456', 0);

INSERT INTO DOCGIA VALUES ('1', 'phamhaingoc1', N'Phạm Ngọc Hải', 'Nam', TO_DATE('1996-01-01', 'YYYY-MM-DD'), N'510 Lý Thái Tổ');
INSERT INTO DOCGIA VALUES ('2', 'phamhaingoc2', N'Phạm Hải NGọc', 'Nam', TO_DATE('1994-01-01', 'YYYY-MM-DD'), N'20 Lý Thái Tổ');
INSERT INTO DOCGIA VALUES ('3', 'phamminhhoang', N'Phạm Minh Hoang', 'Nam', TO_DATE('1994-01-01', 'YYYY-MM-DD'), N'20 Lý Thái Tổ');
INSERT INTO DOCGIA VALUES ('7', 'nguoithanhhai', N'Nguyễn Thanh Hải', N'Nữ', TO_DATE('1994-01-01', 'YYYY-MM-DD'), N'201 Lý Thái Tổ');
INSERT INTO DOCGIA VALUES ('15', 'nguyenhuuhoanghieu', N'Nguyễn Hữu Hoàng Hiếu', N'Nữ', TO_DATE('1994-01-01', 'YYYY-MM-DD'), N'120 Lý Thái Tổ');

INSERT INTO SACH VALUES ('1', N'Lập trình hướng đối tượng', N'Không biết', N'Lập trình', N'HCMUS', 1500, 3, N'Còn', NULL);
INSERT INTO SACH VALUES ('2', N'Nhập môn lập trình', N'Không biết', N'Lập trình', N'HCMUS', 1500, 10, N'Còn', NULL);
INSERT INTO SACH VALUES ('3', N'Kỹ thuật lập trình', N'Không biết', N'Lập trình', N'HCMUS', 1500, 5, N'Còn', NULL);