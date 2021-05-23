package com.wms.service;

import java.util.Date;
import java.util.List;

import com.wms.dto.DoanhThuNgayDTO;

import org.springframework.stereotype.Service;

@Service
public class BaoCaoService {
    
    public double tinhTongDoanhThuThang(Date thang){
        return 0.0;
    }

    public List<DoanhThuNgayDTO> thongKeDanhSachDoanhThuTheoNgayTrongThang(Date thang){
        return null;
    }

    public double tinhTyLeDoanhThuTheoNgayTrongThang(Date thang){
        return 0.0;
    }

    public double tinhDoanhThuTheoNgayTrongThang(Date ngay){
        return 0.0;
    }

    public int tinhTongSoLuongTiecCuoiTrongThang(Date thang){
        return 0;
    }
}
