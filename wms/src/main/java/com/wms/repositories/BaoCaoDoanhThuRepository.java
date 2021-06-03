package com.wms.repositories;

import com.wms.entities.BaoCaoDoanhThu;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaoCaoDoanhThuRepository extends PagingAndSortingRepository<BaoCaoDoanhThu, String> {
    
}
