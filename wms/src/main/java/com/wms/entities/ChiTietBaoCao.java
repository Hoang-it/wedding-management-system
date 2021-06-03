package com.wms.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietBaoCao  {
    @Id
    @Column(name = "ngay_thanhtoan")
    private LocalDate ngayThanhToan;

    @Column(name = "sl_tieccuoi")
    private int soLuongTiecCuoi;

    @Column(name = "doanh_thu")
    private BigDecimal doanhThu;

    @Column(name = "ti_le")
    private double tiLe;

    @ManyToOne
    @JoinColumn(name = "baocao_doanhthu", foreignKey = @ForeignKey(name ="FK_CHITIEBAOCAO"))
    private BaoCaoDoanhThu baoCaoDoanhThu;

    @OneToMany(mappedBy = "ngayThanhToan")
    private Set<HoaDon> dsHoaDon;

}
