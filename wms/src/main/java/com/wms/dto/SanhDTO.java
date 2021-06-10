package com.wms.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanhDTO {
    
    private String maSanh;

    @NotBlank
    @Size(min = 3, max = 50)
    private String tenSanh;

    @NotBlank
    private String loaiSanh;
    
    @Min(0)
    private Long soLuongBanToiDa;

    private BigDecimal donGiaBanToiThieu;
    
    private String ghiChu;
}
