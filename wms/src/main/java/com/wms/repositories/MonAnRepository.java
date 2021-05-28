package com.wms.repositories;

import com.wms.entities.MonAn;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonAnRepository extends PagingAndSortingRepository<MonAn, String> {
    
}
