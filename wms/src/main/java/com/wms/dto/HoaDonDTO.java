package com.wms.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonDTO {
    private String tenChuRe;
    private String tenCoDau;
    private Date ngayThanhToan;
    private int soLuongBan;
    private double donGiaban;
    private double tongTienBan;
    private double tongTienHoaDon;
    private double tongTienDichVu;
    private double tongTienMonAn;
    private double tienDatCoc;
    private double conLai;
    private List<MonAnDTO> monAn;
    private List<DichVuDTO> dichVu;
}
