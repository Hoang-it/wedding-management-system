package com.wms.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ma_tiec_cuoi", "ma_mon_an"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietMonAn implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietMA;

    @ManyToOne
    @JoinColumn(name = "ma_tiec_cuoi", referencedColumnName = "maTiecCuoi", columnDefinition = "varchar(4)")
    private TiecCuoi maTiecCuoi;
    
    @ManyToOne
    @JoinColumn(name = "ma_mon_an",  referencedColumnName = "maMonAn",  columnDefinition = "varchar(4)")
    private MonAn maMonAn;
    
    @Column
    @Lob
    private String ghiChu;

    @Column(precision = 15, scale = 2)
    private BigDecimal donGiaMonAn;

    public ChiTietMonAn(TiecCuoi tiecCuoi, MonAn monAn, String ghiChu){
        this.maTiecCuoi = tiecCuoi;
        this.maMonAn = monAn;
        this.ghiChu = ghiChu;
    }
}
