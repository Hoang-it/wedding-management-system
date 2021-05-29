package com.wms.repositories;

import com.wms.entities.ThamSo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThamSoRepository extends PagingAndSortingRepository<ThamSo, String> {
    
}
