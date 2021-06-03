package com.wms.repositories;

import java.time.LocalDate;
import java.util.List;

import com.wms.entities.TiecCuoi;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiecCuoiRepository extends PagingAndSortingRepository<TiecCuoi, String>{
    List<TiecCuoi> findAllByNgayDaiTiec(LocalDate ngayDaiTiec);
}
