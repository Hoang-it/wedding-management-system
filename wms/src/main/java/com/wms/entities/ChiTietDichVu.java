package com.wms.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
    @UniqueConstraint(columnNames = {"ma_tieccuoi", "ma_dichvu"})
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
    @JoinColumn(name = "ma_tieccuoi", referencedColumnName = "ma_tieccuoi",  columnDefinition = "varchar(4)", foreignKey = @ForeignKey(name = "FK_CTDV_TIECCUOI"))
    private TiecCuoi maTiecCuoi;

    @ManyToOne
    @JoinColumn(name = "ma_dichvu", referencedColumnName = "ma_dichvu",  columnDefinition = "varchar(4)", foreignKey = @ForeignKey(name = "FK_CTDV_DICHVU"))
    private DichVu maDichVu;

    @Column(name = "soluong")
    private Long soLuong;

    @Column(precision = 15, scale = 2, name = "dongia_dichvu")
    private BigDecimal donGiaDichVu;

    @Column(precision = 15, scale = 2, name = "thanhtien")
    private BigDecimal thanhTien;

    public ChiTietDichVu(TiecCuoi tiec, DichVu dichVu, Long soLuong, BigDecimal donGia){
        this.maTiecCuoi = tiec;
        this.maDichVu = dichVu;
        this.soLuong = soLuong;
        this.donGiaDichVu = donGia;

    }
}
