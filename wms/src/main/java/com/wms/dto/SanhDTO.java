package com.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanhDTO {
    private String maSanh;
    private String tenSanh;
    private String loaiSanh;
    private int soLuongBanToiDa;
    private double donGiaBanToiThieu;
    private String ghiChu;
}
