package com.wms.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class HoaDon {
    @Id
    @Column(length = 4, name = "ma_hoadon")
    private String maHoaDon;

    @Column(precision = 15, scale = 2, name = "tongtien_ban")
    private BigDecimal tongTienBan;

    @Column(precision = 15, scale = 2, name = "tongtien_dichvu")
    private BigDecimal tongTienDichVu;

    @Column(precision = 15, scale = 2, name = "tongtien_hoadon")
    private BigDecimal tongTienHoaDon;

    @Column(precision = 15, scale = 2, name = "con_lai")
    private BigDecimal conLai;

    @Column(precision = 15, scale = 2, name = "dongia_ban")
    private BigDecimal donGiaBan;

    @Column(precision = 15, scale = 2, name = "tien_datcoc")
    private BigDecimal tienDatCoc;

    @ManyToOne
    @JoinColumn(name = "ngay_thanhtoan", foreignKey = @ForeignKey(name = "FK_HOADON_CTBC"))
    private ChiTietBaoCao ngayThanhToan;

    @OneToOne
    @JoinColumn(name = "ma_tieccuoi", foreignKey = @ForeignKey(name = "FK_HOADON_TIECCUOI"))
    private TiecCuoi maTiecCuoi;

    public HoaDon(String maHoaDon, LocalDate ngayThanhToan, BigDecimal tongTienBan, BigDecimal donGiaBan,
            BigDecimal tongTienDichVu, BigDecimal tongTienHoaDon, BigDecimal conLai, TiecCuoi maTiecCuoi) {
        this.maHoaDon = maHoaDon;
        this.tongTienBan = tongTienBan;
        this.donGiaBan = donGiaBan;
        this.tongTienDichVu = tongTienDichVu;
        this.tongTienHoaDon = tongTienHoaDon;
        this.conLai = conLai;
        this.maTiecCuoi = maTiecCuoi;
        
    }

    
}
