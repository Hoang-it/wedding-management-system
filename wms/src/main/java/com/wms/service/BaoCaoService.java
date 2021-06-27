package com.wms.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.wms.dto.DoanhThuNgayDTO;
import com.wms.entities.ChiTietBaoCao;
import com.wms.entities.HoaDon;

import org.springframework.stereotype.Service;

@Service
public class BaoCaoService {
    
    public BigDecimal tinhTongDoanhThuThang(Set<ChiTietBaoCao> dsDoanhThuNgay){
        BigDecimal doanhThu = new BigDecimal("0");
        for (ChiTietBaoCao chiTietBaoCao : dsDoanhThuNgay) {
            doanhThu = doanhThu.add(chiTietBaoCao.getDoanhThu());
        }
        System.out.println("TONG DOANH THU THANG : " + doanhThu.doubleValue());
        
        return doanhThu;
    }

    public List<DoanhThuNgayDTO> thongKeDanhSachDoanhThuTheoNgayTrongThang(Date thang){
        return null;
    }

    public double tinhTyLeDoanhThuTheoNgayTrongThang(BigDecimal doanhThuHienTai, BigDecimal doanhThuHomNay){
        return doanhThuHomNay.divide(doanhThuHienTai, 3, RoundingMode.CEILING).doubleValue();
    }

    public BigDecimal tinhDoanhThuTheoNgayTrongThang(Set<HoaDon> dsHoaDon){
        BigDecimal doanhThu = new BigDecimal("0");
        for (HoaDon hoaDon : dsHoaDon) {
            doanhThu = doanhThu.add(hoaDon.getTongTienHoaDon());
        }
        return doanhThu;
    }

    public int tinhTongSoLuongTiecCuoiTrongThang(Set<HoaDon> dsHoaDon){
        return dsHoaDon.size();
    }
}
