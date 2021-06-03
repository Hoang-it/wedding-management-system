package com.wms.repositories;

import com.wms.entities.NguoiDung;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepository extends PagingAndSortingRepository<NguoiDung, Long> {
    NguoiDung findByTenDangNhap(String tenDangNhap);
}
