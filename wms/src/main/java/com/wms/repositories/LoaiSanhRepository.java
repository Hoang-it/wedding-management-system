package com.wms.repositories;

import com.wms.entities.LoaiSanh;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiSanhRepository extends PagingAndSortingRepository<LoaiSanh, String> {
    
}
