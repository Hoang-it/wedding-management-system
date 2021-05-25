package com.wms.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoanhThuNgayDTO {
    private int ngay;
    private int soLuongTiecCuoi;
    private double doanhThuNgay;
    private double tiLe;
}
