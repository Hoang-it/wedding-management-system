package com.wms.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DichVuDTO {
    private String maDichVu;

    @NotBlank
    private String tenDichVu;
    
    private Long soLuong;

    @NotNull
    @PositiveOrZero
    private BigDecimal donGia;

    private BigDecimal thanhTien;
}
