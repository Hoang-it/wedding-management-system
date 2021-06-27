package com.wms.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

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

    @NotBlank
    private String tenMonAn;

    @PositiveOrZero
    private BigDecimal donGia;
    private String ghiChu;
}
