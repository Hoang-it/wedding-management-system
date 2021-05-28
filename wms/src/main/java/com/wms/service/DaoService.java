package com.wms.service;

import java.util.ArrayList;
import java.util.List;

import com.wms.dto.LoaiSanhDTO;
import com.wms.dto.SanhDTO;
import com.wms.entities.DanhSachSanh;
import com.wms.entities.LoaiSanh;
import com.wms.repositories.DanhSachSanhRepository;
import com.wms.repositories.LoaiSanhRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DaoService {
    @Autowired
    LoaiSanhRepository loaiSanhRepository;

    @Autowired
    DanhSachSanhRepository danhSachSanhRepository;

    public List<LoaiSanhDTO> layDanhSachLoaiSanh(){
        List<LoaiSanhDTO> ds = new ArrayList<LoaiSanhDTO>();

        LoaiSanhDTO d = null;
        for (LoaiSanh data : loaiSanhRepository.findAll()) {
            d = new LoaiSanhDTO();
            d.setTenLoaiSanh(data.getTenLoaiSanh());
            d.setMaLoaiSanh(data.getMaSanh());
            d.setDonGiaBanToiThieu(data.getDonGiaBanToiThieu());
            ds.add(d);
        }
        return ds;
    }

    public void datSanhCuoi(SanhDTO sanhMoi){
        DanhSachSanh record = new DanhSachSanh();
        Long row = danhSachSanhRepository.count() + 1;
        String index = row < 10 ? "0" + row.toString() : row.toString();
        record.setMaSanh("SA" + index);
       
        record.setMaLoaiSanh(loaiSanhRepository.findById(sanhMoi.getLoaiSanh()).get());
        record.setTenSanh(sanhMoi.getTenSanh());
        record.setGhiChu(sanhMoi.getGhiChu());
        record.setSoLuongBanToiDa(sanhMoi.getSoLuongBanToiDa());
        danhSachSanhRepository.save(record);
        
        
    }
}
