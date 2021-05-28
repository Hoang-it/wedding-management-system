package com.wms.repositories;

import com.wms.entities.DanhSachSanh;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhSachSanhRepository extends PagingAndSortingRepository<DanhSachSanh, String> {
    
}
