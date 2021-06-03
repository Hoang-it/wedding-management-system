package com.wms.repositories;

import com.wms.entities.HoaDon;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonThanhToanRepository extends PagingAndSortingRepository<HoaDon, String> {
    
}
