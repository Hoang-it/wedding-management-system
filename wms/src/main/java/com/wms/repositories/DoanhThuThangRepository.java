package com.wms.repositories;

import com.wms.entities.DoanhThuThang;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoanhThuThangRepository extends PagingAndSortingRepository<DoanhThuThang, Long> {
    
}
