package com.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DichVuDTO {
    private String maDichVu;
    private String tenDichVu;
    private int soLuong;
    private double donGia;
    private double thanhTien;
}
