package com.wms.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ma_tiec_cuoi", "ma_dich_vu"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDichVu implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietDV;

    @ManyToOne
    @JoinColumn(name = "ma_tiec_cuoi", referencedColumnName = "maTiecCuoi",  columnDefinition = "varchar(4)")
    private TiecCuoi maTiecCuoi;

    @ManyToOne
    @JoinColumn(name = "ma_dich_vu", referencedColumnName = "maDichVu",  columnDefinition = "varchar(4)")
    private DichVu maDichVu;

    @Column
    private Long soLuong;

    @Column(precision = 15, scale = 2)
    private BigDecimal donGiaDichVu;

    public ChiTietDichVu(TiecCuoi tiec, DichVu dichVu, Long soLuong, BigDecimal donGia){
        this.maTiecCuoi = tiec;
        this.maDichVu = dichVu;
        this.soLuong = soLuong;
        this.donGiaDichVu = donGia;

    }
}
