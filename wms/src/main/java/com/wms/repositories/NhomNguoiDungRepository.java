package com.wms.repositories;

import com.wms.entities.NhomNguoiDung;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhomNguoiDungRepository extends PagingAndSortingRepository<NhomNguoiDung, Long> {
    NhomNguoiDung findByMaNhom(String maNhom);
}
