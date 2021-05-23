package com.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanhDTO {
    private String maSanh;
    private String tenSanh;
    private String loaiSanh;
    private int soLuongBanToiDa;
    private double donGiaBanToiThieu;
}
