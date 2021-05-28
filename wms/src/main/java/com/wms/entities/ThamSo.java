package com.wms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class ThamSo {
    @Id
    @Column
    private String tenThamSo;

    @Column
    private Float giaTri;
}
