package com.wms.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class HoaDonThanhToan {
    @Id
    @Column(length = 4)
    private String maHoaDon;
    
    @Column
    private LocalDate ngayThanhToan;

    @Column(precision = 15, scale = 2)
    private BigDecimal tongTienBan;

    @Column(precision = 15, scale = 2)
    private BigDecimal donGiaBan;

    @Column(precision = 15, scale = 2)
    private BigDecimal tongTienDichVu;

    @Column(precision = 15, scale = 2)
    private BigDecimal tongTienHoaDon;

    @Column(precision = 15, scale = 2)
    private BigDecimal conLai;

    @Column(precision = 15, scale = 2)
    private BigDecimal tienDatCoc;

    @OneToOne
    private TiecCuoi maTiecCuoi;

    @ManyToOne
    private DoanhThuThang doanhThuThang;

    public HoaDonThanhToan(String maHoaDon, LocalDate ngayThanhToan, BigDecimal tongTienBan, BigDecimal donGiaBan,
            BigDecimal tongTienDichVu, BigDecimal tongTienHoaDon, BigDecimal conLai, TiecCuoi maTiecCuoi, DoanhThuThang dThang) {
        this.maHoaDon = maHoaDon;
        this.ngayThanhToan = ngayThanhToan;
        this.tongTienBan = tongTienBan;
        this.donGiaBan = donGiaBan;
        this.tongTienDichVu = tongTienDichVu;
        this.tongTienHoaDon = tongTienHoaDon;
        this.conLai = conLai;
        this.maTiecCuoi = maTiecCuoi;
        this.doanhThuThang = dThang;
    }

    
}
