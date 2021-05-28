package com.wms.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Column(length = 4)
    private String maTiecCuoi;

    @Column(length = 50)
    private String tenChuRe;

    @Column(length = 50)
    private String tenCoDau;

    @Column(length = 15)
    private String dienThoai;

    @Column
    private LocalDate ngayDaiTiec;

    @Column(precision = 15, scale = 2)
    private BigDecimal tienDatCoc;

    @Column
    private Long soLuongBan;

    @Column
    private Long soLuongBanDuTru;

    @ManyToOne
    private Ca maCa;

    @ManyToOne
    private DanhSachSanh maSanh;

    @OneToMany(mappedBy = "maTiecCuoi", cascade = CascadeType.ALL)
    private Set<ChiTietMonAn> chiTietMonAn;

    @OneToMany(mappedBy = "maTiecCuoi", cascade = CascadeType.ALL)
    private Set<ChiTietDichVu> chiTietDichVu;

    @OneToOne(mappedBy = "maTiecCuoi")
    private HoaDonThanhToan hoaDonThanhToan;

    public TiecCuoi(String maTiecCuoi, String tenChuRe, String tenCoDau, String sdt, LocalDate ngayDaiTiec, BigDecimal tienDatCoc,
             Long soLuongBan, Long soLuongBanDuTru, Ca ca, DanhSachSanh sanh){
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
