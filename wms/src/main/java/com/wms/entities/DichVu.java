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
public class DichVu {

    @Id
    @Column(length = 4)
    private String maDichVu;

    @Column(length = 50)
    private String tenDichVu;

    @Column(precision = 15, scale = 2)
    private BigDecimal donGia;

    @OneToMany(mappedBy = "maDichVu")
    private Set<ChiTietDichVu> chiTietDichVu;

    public DichVu(String maDichVu, String tenDichVu, BigDecimal donGia){
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.donGia = donGia;
    }

}
