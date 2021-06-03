package com.wms.entities;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PHANQUYEN")
@Getter
@Setter
@NoArgsConstructor
public class PhanQuyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ma_nhom", foreignKey = @ForeignKey(name="FK_PHANQUYEN_NHOMCHUCNANG"), referencedColumnName="ma_nhom")
    private NhomNguoiDung maNhom;

    @ManyToOne    
    @JoinColumn(name = "ma_chuc_nang", foreignKey = @ForeignKey(name="FK_PHANQUYEN_CHUCNANG"), referencedColumnName="ma_chucnang")
    private ChucNang maChucNang;

    public PhanQuyen(NhomNguoiDung nhom, ChucNang chucNang){
        this.maNhom = nhom;
        this.maChucNang = chucNang;
    }
}
