package com.wms.repositories;

import com.wms.entities.TiecCuoi;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiecCuoiRepository extends PagingAndSortingRepository<TiecCuoi, String>{
    
}
