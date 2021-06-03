package com.wms.repositories;

import java.time.LocalDate;

import com.wms.entities.ChiTietBaoCao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietBaoCaoRepository extends PagingAndSortingRepository<ChiTietBaoCao, LocalDate> {
    
}
