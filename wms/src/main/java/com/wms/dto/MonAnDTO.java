package com.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonAnDTO {
    private String maMonAn;
    private String tenMonAn;
    private double donGia;
    private String ghiChu;
}