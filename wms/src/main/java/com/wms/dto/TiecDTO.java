package com.wms.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiecDTO {

    private String maTiecCuoi;

    @NotBlank
    @Size(min = 2, max = 50)
    private String tenChuRe;

    @NotBlank
    @Size(min = 2, max = 50)
    private String tenCoDau;

    @NotBlank
    @Size(min = 10, max = 10)
    private String sdt;

    @NotNull
    private LocalDate ngayDaiTiec;

    private LocalTime gio;

    @NotBlank
    @Size(min = 3, max = 4)
    private String ca;

    @NotBlank
    @Size(min = 4, max = 4)
    private String sanh;

    @Positive
    @NotNull
    private Long soLuongBan;

    @Positive
    @NotNull
    private Long soLuongBanDuTru;

    @Positive
    @NotNull
    private BigDecimal tienDatCoc;  

    private List<MonAnDTO> monAn;
    private List<DichVuDTO> dichVu;

    public TiecDTO(String maTiecCuoi, String tenChuRe, String tenCoDau, String sanh, LocalDate ngayDaiTiec, LocalTime gio, Long soLuongBan){
        this.maTiecCuoi = maTiecCuoi;
        this.tenChuRe = tenChuRe;
        this.tenCoDau = tenCoDau;
        this.sanh = sanh;
        this.ngayDaiTiec = ngayDaiTiec;
        this.gio = gio;
        this.soLuongBan = soLuongBan;
    }
}
