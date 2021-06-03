package com.wms.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"thang", "nam"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaoCaoDoanhThu implements Serializable{
    @Id
    @Column(length = 4, name = "ma_baocao_doanhthu")
    private Long maBaoCaoDoanhThu;

    @Column(name = "thang")
    private int thang;

    @Column(name = "nam")
    private int nam;

    @Column(precision = 15, scale = 2, name = "tong_doanhthu")
    private BigDecimal tongDoanhThu;

    @OneToMany(mappedBy = "baoCaoDoanhThu")
    Set<ChiTietBaoCao> chiTietBaoCao;

    public BaoCaoDoanhThu(int thang, int nam) {
        this.thang = thang;
        this.nam = nam;
    }

    
}
