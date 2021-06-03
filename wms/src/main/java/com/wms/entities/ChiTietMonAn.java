package com.wms.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ma_tieccuoi", "ma_monan"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietMonAn implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietMA;

    @ManyToOne
    @JoinColumn(name = "ma_tieccuoi", referencedColumnName = "ma_tieccuoi", columnDefinition = "varchar(4)", foreignKey = @ForeignKey(name ="FK_CTMA_TIECCUOI"))
    private TiecCuoi maTiecCuoi;
    
    @ManyToOne
    @JoinColumn(name = "ma_monan",  referencedColumnName = "ma_monan",  columnDefinition = "varchar(4)", foreignKey = @ForeignKey(name ="FK_CTMA_MONAN"))
    private MonAn maMonAn;
    
    @Lob
    @Column(name = "ghi_chu")    
    private String ghiChu;

    @Column(precision = 15, scale = 2, name = "dongia_monan")
    private BigDecimal donGiaMonAn;

    public ChiTietMonAn(TiecCuoi tiecCuoi, MonAn monAn, String ghiChu){
        this.maTiecCuoi = tiecCuoi;
        this.maMonAn = monAn;
        this.ghiChu = ghiChu;
    }
}
