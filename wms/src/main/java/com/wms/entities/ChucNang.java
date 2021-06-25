package com.wms.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "CHUCNANG")
@Getter
@Setter
@NoArgsConstructor
public class ChucNang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "ma_chucnang")
    private String maChucNang;

    @Column(name = "ten_chucnang")
    private String tenChucNang;

    @Column(name = "ten_manhinh_duocload")
    private String tenManHinhDuocLoad;

    @OneToMany(mappedBy = "chucNang")    
    Set<NhomNguoiDung> nhomNguoiDung;

    public ChucNang(String maChucNang, String tenChucNang, String tenManHinhDuocLoad){
        this.maChucNang = maChucNang;
        this.tenChucNang = tenChucNang;
        this.tenManHinhDuocLoad = tenManHinhDuocLoad;
    }
}
