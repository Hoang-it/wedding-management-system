package com.wms.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDoanhThuThang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietDoanhThu;
    
    @Column
    private float tiLe;

    @Column
    private Long soLuongTiecCuoi;

   
}
