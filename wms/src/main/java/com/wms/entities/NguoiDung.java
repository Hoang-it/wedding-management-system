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

    @Column(unique = true)
    private String tenNguoiDung;

    @Column
    private String matKhau;

    @ManyToOne
    @JoinColumn(name = "maNhom", foreignKey = @ForeignKey(name ="FK_NGUOIDUNG_NHOMNGUOIDUNG"), referencedColumnName = "maNhom")
    private NhomNguoiDung maNhom;

    public NguoiDung(String string, String encode, NhomNguoiDung nhomNguoiDung) {
        this.tenNguoiDung = string;
        this.matKhau = encode;
        this.maNhom = nhomNguoiDung;
    }

}
