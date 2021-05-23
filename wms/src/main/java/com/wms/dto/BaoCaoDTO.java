package com.wms.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaoCaoDTO {
    private Date ngay;
    private double tongDoanhThu;
    private List<DoanhThuNgayDTO> doanhThuNgay;
}
