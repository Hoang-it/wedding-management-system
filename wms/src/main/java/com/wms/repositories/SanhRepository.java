package com.wms.repositories;

import com.wms.entities.Sanh;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanhRepository extends PagingAndSortingRepository<Sanh, String> {
    
}
