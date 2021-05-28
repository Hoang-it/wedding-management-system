package com.wms.entities;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class DanhSachSanh {
    @Id
    @Column(length = 4)
    private String maSanh;

    @Column(length = 50)
    private String tenSanh;

    @Column
    private Long soLuongBanToiDa;

    @Column
    @Lob
    private String ghiChu;

    @ManyToOne
    private LoaiSanh maLoaiSanh;

    @OneToMany(mappedBy = "maSanh")
    private Set<TiecCuoi> dsTiecCuoi;

    public DanhSachSanh(String maSanh, String tenSanh, Long soLuongBanToiDa, String ghiChu, LoaiSanh maLoaiSanh){
        this.maSanh = maSanh;
        this.tenSanh = tenSanh;
        this.soLuongBanToiDa = soLuongBanToiDa;
        this.ghiChu = ghiChu;
        this.maLoaiSanh = maLoaiSanh;
    }
}
