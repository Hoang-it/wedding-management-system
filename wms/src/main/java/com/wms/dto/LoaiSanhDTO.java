package com.wms.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiSanhDTO {

    @NotBlank
    @Size(min = 4, max = 4)
    private String maLoaiSanh;

    @NotBlank
    @Size(min = 1, max = 50)
    @Pattern(regexp="^\\d*[a-zA-Z][a-zA-Z\\d]*$", message="tên loại sảnh chỉ có thể chứ số hoặc ký tự")
    private String tenLoaiSanh;

    @Positive
    private BigDecimal donGiaBanToiThieu;
}
