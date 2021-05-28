package com.wms.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"thang", "nam"})
})
@Getter
@Setter
@NoArgsConstructor
public class DoanhThuThang implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDoanhThuThang;

    @Column(length = 2)
    private String thang;

    @Column(length = 4)
    private String nam;

    @Column(precision = 15, scale = 2)
    private BigDecimal doanhThu;

    @OneToMany(mappedBy = "doanhThuThang")
    private Set<HoaDonThanhToan> dsHoaDon;

    public DoanhThuThang(String thang, String nam) {
        this.thang = thang;
        this.nam = nam;
    }

    
}
