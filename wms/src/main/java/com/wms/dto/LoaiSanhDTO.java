package com.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiSanhDTO {
    private String maLoaiSanh;
    private String tenLoaiSanh;
    private int soLuongBanToiDa;
    private double donGiaBanToiThieu;
}
