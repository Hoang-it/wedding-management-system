package com.wms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NGUOIDUNG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDung {
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "ten_dangnhap")
    private String tenDangNhap;

    @Column(name = "matkhau")
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "ma_nhom", foreignKey = @ForeignKey(name ="FK_NGUOIDUNG_NHOMNGUOIDUNG"), referencedColumnName = "ma_nhom")
    private NhomNguoiDung maNhom;

    public NguoiDung(String string, String encode, NhomNguoiDung nhomNguoiDung) {
        this.tenDangNhap = string;
        this.matKhau = encode;
        this.maNhom = nhomNguoiDung;
    }

}
