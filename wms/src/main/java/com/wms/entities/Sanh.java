package com.wms.entities;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sanh {
    @Id
    @Column(length = 4, name = "ma_sanh")
    private String maSanh;

    @Column(length = 50, name = "ten_sanh")
    private String tenSanh;

    @Column(name = "sl_ban_toi_da")
    private Long soLuongBanToiDa;

    @Lob
    @Column(name = "ghi_chu")    
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "ma_loai_sanh", foreignKey = @ForeignKey(name = "FK_SANH_LOAISANH"))
    private LoaiSanh maLoaiSanh;

    @OneToMany(mappedBy = "maSanh")
    private Set<TiecCuoi> dsTiecCuoi;

    public Sanh(String maSanh, String tenSanh, Long soLuongBanToiDa, String ghiChu, LoaiSanh maLoaiSanh){
        this.maSanh = maSanh;
        this.tenSanh = tenSanh;
        this.soLuongBanToiDa = soLuongBanToiDa;
        this.ghiChu = ghiChu;
        this.maLoaiSanh = maLoaiSanh;
    }
}
