package com.wms.repositories;

import java.util.Set;

import com.wms.entities.PhanQuyen;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhanQuyenRepository extends PagingAndSortingRepository<PhanQuyen, Long> {
    Set<PhanQuyen> findAllByMaNhom(String maNhom);
}
