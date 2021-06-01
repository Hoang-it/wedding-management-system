package com.wms.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.wms.entities.ChiTietDichVu;
import com.wms.entities.ChiTietMonAn;
import com.wms.entities.ThamSo;
import com.wms.repositories.ThamSoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoaDonService {
    @Autowired
    ThamSoRepository thamSoRepository;

    public BigDecimal tinhDonGiaBan(Set<ChiTietMonAn> thucDon){
        BigDecimal donGiaBan = new BigDecimal("0.0");
        for (ChiTietMonAn chiTietMonAn : thucDon) {
            donGiaBan = donGiaBan.add(chiTietMonAn.getDonGiaMonAn());
        }
        System.out.println("DON GIA BAN : " + donGiaBan.doubleValue());
        return donGiaBan;
    }

    public BigDecimal tinhTongTienBan(BigDecimal donGiaBan, Long soLuongBan, Long soLuongBanDuTru){
        BigDecimal tongTienBan = donGiaBan.multiply(new BigDecimal(soLuongBan + soLuongBanDuTru));
        System.out.println("TONG TIEN BAN : " + tongTienBan.doubleValue());
        return tongTienBan;
    }

    public BigDecimal tinhTongTienDichVu(Set<ChiTietDichVu> dsDichVu, Long ngayTre){
        BigDecimal tongTienDichVu = new BigDecimal("0.0");
        BigDecimal tienPhat = new BigDecimal("1.0");
        
        ThamSo apDungQuyDinh = thamSoRepository.findById("KiemTraNgayThanhToan").get();
        BigDecimal mucPhat = new BigDecimal(thamSoRepository.findById("TiLePhanTramPhat").get().getGiaTri());

        for (ChiTietDichVu chiTietDichVu : dsDichVu) {
            tongTienDichVu = tongTienDichVu.add(chiTietDichVu.getThanhTien());
        }
        if ("true".equals(apDungQuyDinh.getGiaTri())){
            BigDecimal phanTramPhat = mucPhat.multiply(new BigDecimal(ngayTre));
            System.out.println("PHẦN TRĂM PHẠT : " + phanTramPhat.doubleValue());
            tienPhat = phanTramPhat.multiply(tongTienDichVu);
            System.out.println("TIỀN PHẠT : " + tienPhat.doubleValue());
            tongTienDichVu = tongTienDichVu.add(tienPhat);
        }
        
        System.out.println("TONG TIEN DICH VU : " + tongTienDichVu.doubleValue());
        return tongTienDichVu;
    }

    public BigDecimal tinhTongTienHoaDon(BigDecimal tienBan, BigDecimal tienDichVu){
        BigDecimal tongTienHoaDon = tienBan.add(tienDichVu);
        return tongTienHoaDon;
    }

    public BigDecimal tinhTienConLai(BigDecimal tienHoaDon, BigDecimal tienDatCoc){
        BigDecimal conLai = tienHoaDon.subtract(tienDatCoc);
        return conLai;
    }
    
}
