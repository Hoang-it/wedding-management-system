package com.wms.entities;

import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Ca {
    @Id
    @Column(length = 4)
    private String maCa;

    @Column(length = 10)
    private String tenCa;

    @Column
    private LocalTime gioBatDau;

    @Column
    private LocalTime gioKetThuc;

    @OneToMany(mappedBy = "maCa")
    private Set<TiecCuoi> dsTiecCuoi;

    public Ca(String maCa, String tenCa, LocalTime gioBatDau, LocalTime gioKetThuc){
        this.maCa = maCa;
        this.tenCa = tenCa;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }
}
