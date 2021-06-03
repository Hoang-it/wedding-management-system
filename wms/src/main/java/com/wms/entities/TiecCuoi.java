package com.wms.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class TiecCuoi {
    @Id
    @Column(length = 4, name = "ma_tieccuoi")
    private String maTiecCuoi;

    @Column(length = 50, name = "ten_chu_re")
    private String tenChuRe;

    @Column(length = 50, name = "ten_co_dau")
    private String tenCoDau;

    @Column(length = 15, name = "dien_thoai")
    private String dienThoai;

    @Column(name = "ngay_dat_tiec")
    private LocalDate ngayDatTiec;

    @Column(name = "ngay_dai_tiec")
    private LocalDate ngayDaiTiec;

    @Column(precision = 15, scale = 2, name = "tienDatCoc")
    private BigDecimal tienDatCoc;

    @Column(name = "sl_ban")
    private Long soLuongBan;

    @Column(name = "sl_ban_dutru")
    private Long soLuongBanDuTru;

    @ManyToOne
    @JoinColumn(name = "ma_ca", foreignKey = @ForeignKey(name = "FK_TIECCUOI_CA"))
    private Ca maCa;

    @ManyToOne
    @JoinColumn(name = "ma_sanh", foreignKey = @ForeignKey(name = "FK_TIECCUOI_SANH"))
    private Sanh maSanh;

    @OneToMany(mappedBy = "maTiecCuoi", cascade = CascadeType.ALL)
    private Set<ChiTietMonAn> chiTietMonAn;

    @OneToMany(mappedBy = "maTiecCuoi", cascade = CascadeType.ALL)
    private Set<ChiTietDichVu> chiTietDichVu;

    @OneToOne(mappedBy = "maTiecCuoi")
    private HoaDon hoaDonThanhToan;

    public TiecCuoi(String maTiecCuoi, String tenChuRe, String tenCoDau, String sdt, LocalDate ngayDaiTiec, BigDecimal tienDatCoc,
             Long soLuongBan, Long soLuongBanDuTru, Ca ca, Sanh sanh){
        this.maTiecCuoi = maTiecCuoi;
        this.tenChuRe = tenChuRe;
        this.tenCoDau = tenCoDau;
        this.dienThoai = sdt;
        this.ngayDaiTiec = ngayDaiTiec;
        this.tienDatCoc = tienDatCoc;
        this.soLuongBan = soLuongBan;
        this.soLuongBanDuTru = soLuongBanDuTru;
        this.maCa = ca;
        this.maSanh = sanh;
    }
}
