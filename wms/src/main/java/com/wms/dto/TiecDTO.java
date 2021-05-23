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
    private String tenChuRe;
    private String tenCoDau;
    private String sdt;
    private Date ngayDaiTiec;
    private Date gio;
    private String ca;
    private String sanh;
    private int soLuongBan;
    private int soLuongBanDuTru;
    private double tienDatCoc;  
    private List<String> monAn;
    private List<String> dichVu;
}
