package com.wms.dto;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaDTO {

    @NotBlank
    private String tenCa;

    @NotNull
    private LocalTime gioBatDau;

    @NotNull
    private LocalTime gioKetThuc;    

    private String maCa;
}
