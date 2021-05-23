package com.wms.repositories;

import com.wms.entities.ChucNang;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucNangRepository extends PagingAndSortingRepository<ChucNang, Long> {
    ChucNang findByMaChucNang(String maChucNang);
}
