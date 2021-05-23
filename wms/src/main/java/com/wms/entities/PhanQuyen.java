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
    @JoinColumn(name = "maNhom", foreignKey = @ForeignKey(name="FK_PHANQUYEN_NHOMCHUCNANG"), referencedColumnName="maNhom")
    private NhomNguoiDung maNhom;

    @ManyToOne    
    @JoinColumn(name = "maChucNang", foreignKey = @ForeignKey(name="FK_PHANQUYEN_CHUCNANG"), referencedColumnName="maChucNang")
    private ChucNang maChucNang;

    public PhanQuyen(NhomNguoiDung nhom, ChucNang chucNang){
        this.maNhom = nhom;
        this.maChucNang = chucNang;
    }
}
