package com.wms.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NHOM_NGUOIDUNG")
@Getter
@Setter
@NoArgsConstructor
public class NhomNguoiDung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String maNhom;

    @Column
    private String tenNhom;

    @OneToMany(mappedBy = "maNhom")
    Set<NguoiDung> dsNguoiDung;

    @OneToMany(mappedBy = "maNhom", fetch = FetchType.EAGER)
    Set<PhanQuyen> phanQuyenIds = new HashSet<>();

    public NhomNguoiDung(String maNhom, String tenNhom){
        this.maNhom = maNhom;
        this.tenNhom = tenNhom;
    }
}
