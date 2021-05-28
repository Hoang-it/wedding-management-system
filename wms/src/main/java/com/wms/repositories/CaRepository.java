package com.wms.repositories;

import com.wms.entities.Ca;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaRepository extends PagingAndSortingRepository<Ca, String> {
    
}
