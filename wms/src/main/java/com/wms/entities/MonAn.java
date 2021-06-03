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
public class MonAn {
    @Id
    @Column(length = 4, name = "ma_monan")
    private String maMonAn;

    @Column(length = 50, name = "ten_monan")
    private String tenMonAn;

    @Column(precision = 15, scale = 2, name = "don_gia")
    private BigDecimal donGia;

    @OneToMany(mappedBy = "maMonAn")
    private Set<ChiTietMonAn> chiTietMonAn;

    public MonAn(String maMonAn, String tenMonAn, BigDecimal donGia){
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
    }
}
