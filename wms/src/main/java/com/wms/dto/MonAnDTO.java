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
public class MonAnDTO {
    private String maMonAn;
    private String tenMonAn;
    private BigDecimal donGia;
    private String ghiChu;
}
