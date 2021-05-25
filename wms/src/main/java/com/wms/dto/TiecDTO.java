package com.wms.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiecDTO {
    private String maTiecCuoi;
    private String tenChuRe;
    private String tenCoDau;
    private String sdt;
    private Date ngayDaiTiec;
    private int gio;
    private String ca;
    private String sanh;
    private int soLuongBan;
    private int soLuongBanDuTru;
    private double tienDatCoc;  
    private List<String> monAn;
    private List<String> dichVu;

    public TiecDTO(String maTiecCuoi, String tenChuRe, String tenCoDau, String sanh, Date ngayDaiTiec, int gio, int soLuongBan){
        this.maTiecCuoi = maTiecCuoi;
        this.tenChuRe = tenChuRe;
        this.tenCoDau = tenCoDau;
        this.sanh = sanh;
        this.ngayDaiTiec = ngayDaiTiec;
        this.gio = gio;
        this.soLuongBan = soLuongBan;
    }
}
