package com.wms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonDTO {
    private String maHoaDon;
    private String maTiecCuoi;
    private String tenChuRe;
    private String tenCoDau;
    private String soDienThoai;
    private String ca;
    private String sanh;
    private LocalDate ngayThanhToan;
    private LocalDate ngayDaiTiec;
    private Long soLuongBan;
    private Long soLuongBanDuTru;
    private Long ngayTreHan;
    private BigDecimal donGiaBan;
    private BigDecimal tongTienBan;
    private BigDecimal tienPhat;
    private BigDecimal tongTienHoaDon;
    private BigDecimal tongTienDichVu;
    private BigDecimal tongTienMonAn;
    private BigDecimal tienDatCoc;
    private BigDecimal conLai;
    private List<MonAnDTO> monAn;
    private List<DichVuDTO> dichVu;
}
