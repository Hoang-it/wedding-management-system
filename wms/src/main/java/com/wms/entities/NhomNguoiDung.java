package com.wms.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NHOM_NGUOIDUNG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhomNguoiDung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "ma_nhom")
    private String maNhom;

    @Column(name = "ten_nhom")
    private String tenNhom;

    @OneToMany(mappedBy = "maNhom")
    Set<NguoiDung> dsNguoiDung;

    @ManyToOne
    ChucNang chucNang;

    public NhomNguoiDung(String maNhom, String tenNhom, ChucNang chucNang){
        this.maNhom = maNhom;
        this.tenNhom = tenNhom;
        this.chucNang = chucNang;
    }
}
