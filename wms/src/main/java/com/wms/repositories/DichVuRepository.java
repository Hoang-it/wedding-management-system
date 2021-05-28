package com.wms.repositories;

import com.wms.entities.DichVu;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DichVuRepository extends PagingAndSortingRepository<DichVu, String> {
    
}
