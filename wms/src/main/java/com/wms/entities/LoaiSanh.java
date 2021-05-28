package com.wms.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class LoaiSanh {
    @Id
    @Column(length = 4)
    private String maSanh;

    @Column(length = 50)
    private String tenLoaiSanh;

    @Column(precision = 10, scale = 2)
    private BigDecimal donGiaBanToiThieu;

    @OneToMany(mappedBy = "maLoaiSanh")
    private Set<DanhSachSanh> dsSanh;

    public LoaiSanh(String maSanh, String tenLoaiSanh, BigDecimal donGiaBanToiThieu){
        this.maSanh = maSanh;
        this.tenLoaiSanh = tenLoaiSanh;
        this.donGiaBanToiThieu = donGiaBanToiThieu;
    }
}
