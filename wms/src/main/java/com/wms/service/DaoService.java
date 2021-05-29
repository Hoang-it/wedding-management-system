package com.wms.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wms.dto.CaDTO;
import com.wms.dto.DichVuDTO;
import com.wms.dto.LoaiSanhDTO;
import com.wms.dto.MonAnDTO;
import com.wms.dto.SanhDTO;
import com.wms.dto.TiecDTO;
import com.wms.entities.Ca;
import com.wms.entities.ChiTietDichVu;
import com.wms.entities.ChiTietMonAn;
import com.wms.entities.DanhSachSanh;
import com.wms.entities.DichVu;
import com.wms.entities.LoaiSanh;
import com.wms.entities.MonAn;
import com.wms.entities.TiecCuoi;
import com.wms.repositories.CaRepository;
import com.wms.repositories.DanhSachSanhRepository;
import com.wms.repositories.DichVuRepository;
import com.wms.repositories.LoaiSanhRepository;
import com.wms.repositories.MonAnRepository;
import com.wms.repositories.TiecCuoiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DaoService {
    @Autowired
    LoaiSanhRepository loaiSanhRepository;

    @Autowired
    DanhSachSanhRepository danhSachSanhRepository;

    @Autowired
    CaRepository caRepository;

    @Autowired
    TiecCuoiRepository tiecCuoiRepository;

    @Autowired
    MonAnRepository monAnRepository;

    @Autowired
    DichVuRepository dichVuRepository;

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

    public List<CaDTO> layToanBoDanhSachCa(){
        List<CaDTO> dsCa = new ArrayList<>();
        CaDTO data = null;
        for (Ca ca : caRepository.findAll()) {
            data = new CaDTO();
            data.setMaCa(ca.getMaCa());
            data.setTenCa(ca.getTenCa());
            dsCa.add(data);
        }
        return dsCa;
    }

    public List<SanhDTO> layToanBoDanhSachSanh(){
        List<SanhDTO> dsSanh = new ArrayList<>();
        SanhDTO data = null;
        for (DanhSachSanh sanh : danhSachSanhRepository.findAll()) {
            data = new SanhDTO();
            data.setMaSanh(sanh.getMaSanh());
            data.setTenSanh(sanh.getTenSanh());
            dsSanh.add(data);
        }
        return dsSanh;
    }

    public List<MonAnDTO> layToanBoDanhSachMonAn(){
        List<MonAnDTO> dsMonAn = new ArrayList<>();
        MonAnDTO data = null;
        for (MonAn monAn : monAnRepository.findAll()) {
            data = new MonAnDTO();
            data.setMaMonAn(monAn.getMaMonAn());
            data.setTenMonAn(monAn.getTenMonAn());
            data.setDonGia(monAn.getDonGia());
            
            dsMonAn.add(data);
        }
        return dsMonAn;
    }

    public List<DichVuDTO> layToanBoDanhSachDichVu(){
        List<DichVuDTO> dsDichVu = new ArrayList<>();
        DichVuDTO data = null;
        for (DichVu dichVu : dichVuRepository.findAll()) {
            data = new DichVuDTO();
            data.setMaDichVu(dichVu.getMaDichVu());
            data.setTenDichVu(dichVu.getTenDichVu());
            data.setDonGia(dichVu.getDonGia());
            dsDichVu.add(data);
        }
        return dsDichVu;
    }

    public void datTiecCuoi(TiecDTO tiecCuoi){
        TiecCuoi record = new TiecCuoi();
        Long row = tiecCuoiRepository.count() + 1;
        String index = row < 10 ? "0" + row.toString() : row.toString();
        Set<ChiTietMonAn> thucDon = new HashSet<>();
        Set<ChiTietDichVu> dsDichVu = new HashSet<>();

        record.setMaTiecCuoi("TC" + index);
       
        record.setTenChuRe(tiecCuoi.getTenChuRe());
        record.setTenCoDau(tiecCuoi.getTenCoDau());
        record.setDienThoai(tiecCuoi.getSdt());
        record.setNgayDaiTiec(tiecCuoi.getNgayDaiTiec());
        record.setMaCa(caRepository.findById(tiecCuoi.getCa()).get());
        record.setMaSanh(danhSachSanhRepository.findById(tiecCuoi.getSanh()).get());
        record.setSoLuongBan(tiecCuoi.getSoLuongBan());
        record.setSoLuongBanDuTru(tiecCuoi.getSoLuongBanDuTru());
        record.setTienDatCoc(tiecCuoi.getTienDatCoc());

        for (DichVuDTO dichVu : tiecCuoi.getDichVu()) {
            ChiTietDichVu data = new ChiTietDichVu();
            data.setMaTiecCuoi(record);
            data.setMaDichVu(dichVuRepository.findById(dichVu.getMaDichVu()).get());
            data.setDonGiaDichVu(dichVuRepository.findById(dichVu.getMaDichVu()).get().getDonGia());
            data.setSoLuong(dichVu.getSoLuong());
            dsDichVu.add(data);
        }

        for (MonAnDTO monAn : tiecCuoi.getMonAn()) {
            ChiTietMonAn data = new ChiTietMonAn();
            data.setMaTiecCuoi(record);
            data.setMaMonAn(monAnRepository.findById(monAn.getMaMonAn()).get());
            data.setDonGiaMonAn(monAnRepository.findById(monAn.getMaMonAn()).get().getDonGia());
            data.setGhiChu(monAn.getGhiChu());
            thucDon.add(data);
        }

        record.setChiTietDichVu(dsDichVu);
        record.setChiTietMonAn(thucDon);
        tiecCuoiRepository.save(record);
        
    }
}
