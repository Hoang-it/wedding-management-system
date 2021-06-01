package com.wms.dto;

import java.math.BigDecimal;

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
    private BigDecimal donGiaBanToiThieu;
}
