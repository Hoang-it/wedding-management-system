package com.wms.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wms.dto.CaDTO;
import com.wms.dto.DichVuDTO;
import com.wms.dto.HoaDonDTO;
import com.wms.dto.LoaiSanhDTO;
import com.wms.dto.MonAnDTO;
import com.wms.dto.SanhDTO;
import com.wms.dto.TiecDTO;
import com.wms.entities.Ca;
import com.wms.entities.ChiTietDichVu;
import com.wms.entities.ChiTietMonAn;
import com.wms.entities.DanhSachSanh;
import com.wms.entities.DichVu;
import com.wms.entities.HoaDonThanhToan;
import com.wms.entities.LoaiSanh;
import com.wms.entities.MonAn;
import com.wms.entities.ThamSo;
import com.wms.entities.TiecCuoi;
import com.wms.repositories.CaRepository;
import com.wms.repositories.DanhSachSanhRepository;
import com.wms.repositories.DichVuRepository;
import com.wms.repositories.HoaDonThanhToanRepository;
import com.wms.repositories.LoaiSanhRepository;
import com.wms.repositories.MonAnRepository;
import com.wms.repositories.ThamSoRepository;
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

    @Autowired
    HoaDonThanhToanRepository hoaDonThanhToanRepository;

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    ThamSoRepository thamSoRepository;
    
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
            data.setGioBatDau(ca.getGioBatDau());
            data.setGioKetThuc(ca.getGioKetThuc());
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
        HoaDonThanhToan hoaDon = new HoaDonThanhToan();  
        Long rowHD = hoaDonThanhToanRepository.count() + 1;       
        String indexHD = rowHD < 10 ? "0" + rowHD.toString() : rowHD.toString();
        Set<ChiTietMonAn> thucDon = new HashSet<>();
        Set<ChiTietDichVu> dsDichVu = new HashSet<>();
        Long rowTC = tiecCuoiRepository.count() + 1;
        String indexTC = rowTC < 10 ? "0" + rowTC.toString() : rowTC.toString();

        record.setMaTiecCuoi("TC" + indexTC);
       
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
            data.setThanhTien(data.getDonGiaDichVu().multiply(new BigDecimal(data.getSoLuong())));
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

        hoaDon.setMaHoaDon("HD" + indexHD);
        hoaDon.setMaTiecCuoi(record);

        tiecCuoiRepository.save(record);      
        hoaDonThanhToanRepository.save(hoaDon);  
    }

    public List<TiecDTO> layToanBoDanhSachTiecCuoi(){
        List<TiecDTO> dsTiecCuoi = new ArrayList<>();
        TiecDTO data = null;
        for (TiecCuoi tiecCuoi : tiecCuoiRepository.findAll()) {
            data = new TiecDTO();
            data.setMaTiecCuoi(tiecCuoi.getMaTiecCuoi());
            data.setTenCoDau(tiecCuoi.getTenCoDau());
            data.setTenChuRe(tiecCuoi.getTenChuRe());
            data.setSanh(tiecCuoi.getMaSanh().getTenSanh());
            data.setNgayDaiTiec(tiecCuoi.getNgayDaiTiec());
            data.setGio(tiecCuoi.getMaCa().getGioBatDau());
            data.setSoLuongBan(tiecCuoi.getSoLuongBan());

            dsTiecCuoi.add(data);
        }
        return dsTiecCuoi;
    }

    public LoaiSanhDTO layThongTinLoaiSanh(String maLoaiSanh){
        LoaiSanhDTO model = new LoaiSanhDTO();
        LoaiSanh data = loaiSanhRepository.findById(maLoaiSanh).get();
        
        model.setMaLoaiSanh(data.getMaSanh());
        model.setTenLoaiSanh(data.getTenLoaiSanh());
        model.setDonGiaBanToiThieu(data.getDonGiaBanToiThieu());
        return model;
    }

    public CaDTO layThongTinCa(String maCa){
        CaDTO model = new CaDTO();
        Ca data = caRepository.findById(maCa).get();
        model.setMaCa(data.getMaCa());
        model.setTenCa(data.getTenCa());
        model.setGioBatDau(data.getGioBatDau());
        model.setGioKetThuc(data.getGioKetThuc());
        return model;
    }

    public void capNhatThongTinLoaiSanh(LoaiSanhDTO sanh){
        LoaiSanh record = loaiSanhRepository.findById(sanh.getMaLoaiSanh()).get();
        record.setTenLoaiSanh(sanh.getTenLoaiSanh());
        record.setDonGiaBanToiThieu(sanh.getDonGiaBanToiThieu());        
        loaiSanhRepository.save(record);
    }

    public void themLoaiSanh(LoaiSanhDTO sanh){
        LoaiSanh record = new LoaiSanh();
        Long row = loaiSanhRepository.count();
        String index = row < 10 ? "0" + row.toString() : row.toString();

        record.setMaSanh("LS" + row);
        record.setTenLoaiSanh(sanh.getTenLoaiSanh());
        record.setDonGiaBanToiThieu(sanh.getDonGiaBanToiThieu());        
        loaiSanhRepository.save(record);
    }

    public void xoaThongTinLoaiSanh(String maLoaiSanh){
        loaiSanhRepository.deleteById(maLoaiSanh);
    }

    public void capNhatThongTinCa(CaDTO ca){
        Ca record = caRepository.findById(ca.getMaCa()).get();
        record.setTenCa(ca.getTenCa());
        record.setGioBatDau(ca.getGioBatDau());
        record.setGioKetThuc(ca.getGioKetThuc());
        caRepository.save(record);
    }
    public HoaDonDTO layThongTinHoaDon(String maTiecCuoi){
        TiecCuoi tiecCuoi = tiecCuoiRepository.findById(maTiecCuoi).get();

        HoaDonDTO data = new HoaDonDTO();
        List<DichVuDTO> dsDichVu = new ArrayList<>();
        List<MonAnDTO> dsMonAn = new ArrayList<>();
        Long ngayTreHan = 0l;
        DichVuDTO dichVu = null;
        MonAnDTO monAn = null;
        BigDecimal tienPhat = new BigDecimal("1.0");
        
        ThamSo apDungQuyDinh = thamSoRepository.findById("KiemTraNgayThanhToan").get();
        BigDecimal mucPhat = new BigDecimal(thamSoRepository.findById("TiLePhanTramPhat").get().getGiaTri());

        data.setTenCoDau(tiecCuoi.getTenCoDau());
        data.setTenChuRe(tiecCuoi.getTenChuRe());
        data.setSoLuongBan(tiecCuoi.getSoLuongBan());
        data.setNgayThanhToan(tiecCuoi.getNgayDaiTiec());

        data.setTienDatCoc(tiecCuoi.getTienDatCoc());
        data.setNgayThanhToan(LocalDate.now());

        ngayTreHan = ChronoUnit.DAYS.between(tiecCuoi.getNgayDaiTiec(), data.getNgayThanhToan());
        data.setNgayTreHan(ngayTreHan);
        
        for (ChiTietDichVu ctDichVu : tiecCuoi.getChiTietDichVu()) {
            dichVu = new DichVuDTO();
            dichVu.setMaDichVu(ctDichVu.getMaDichVu().getMaDichVu());
            dichVu.setTenDichVu(ctDichVu.getMaDichVu().getTenDichVu());
            dichVu.setSoLuong(ctDichVu.getSoLuong());
            dichVu.setDonGia(ctDichVu.getDonGiaDichVu());
            dichVu.setThanhTien(ctDichVu.getThanhTien().add(ctDichVu.getThanhTien().multiply(tienPhat.multiply(new BigDecimal(ngayTreHan)))));
            dsDichVu.add(dichVu);
        }
        data.setDichVu(dsDichVu);

        for (ChiTietMonAn ctMonAn : tiecCuoi.getChiTietMonAn()) {
            monAn = new MonAnDTO();
            monAn.setMaMonAn(ctMonAn.getMaMonAn().getMaMonAn());
            monAn.setTenMonAn(ctMonAn.getMaMonAn().getTenMonAn());
            monAn.setDonGia(ctMonAn.getDonGiaMonAn());
            dsMonAn.add(monAn);
        }
        data.setMonAn(dsMonAn);

        data.setDonGiaBan(hoaDonService.tinhDonGiaBan(tiecCuoi.getChiTietMonAn()));
        data.setTongTienBan(hoaDonService.tinhTongTienBan(data.getDonGiaBan(), tiecCuoi.getSoLuongBan(), tiecCuoi.getSoLuongBanDuTru()));
        data.setTongTienDichVu(hoaDonService.tinhTongTienDichVu(tiecCuoi.getChiTietDichVu(), ngayTreHan));
        data.setTongTienHoaDon(hoaDonService.tinhTongTienHoaDon(data.getTongTienBan(), data.getTongTienDichVu()));
        data.setTongTienMonAn(data.getDonGiaBan());
        data.setConLai(hoaDonService.tinhTienConLai(data.getTongTienHoaDon(), tiecCuoi.getTienDatCoc()));
        
        return data;
    }

    public void lapHoaDon(HoaDonDTO data){
        TiecCuoi tiecCuoi = tiecCuoiRepository.findById(data.getMaTiecCuoi()).get();
        HoaDonThanhToan hoaDon = tiecCuoi.getHoaDonThanhToan();  
        
        hoaDon.setTienDatCoc(data.getTienDatCoc());        
        hoaDon.setNgayThanhToan(data.getNgayThanhToan());

        hoaDon.setDonGiaBan(data.getDonGiaBan());
        hoaDon.setTongTienBan(data.getTongTienBan());
        hoaDon.setTongTienDichVu(data.getTongTienDichVu());
        hoaDon.setTongTienHoaDon(data.getTongTienHoaDon());
        hoaDon.setConLai(data.getConLai());

        for (ChiTietDichVu ctDv : tiecCuoi.getChiTietDichVu()) {
            for (DichVuDTO dv : data.getDichVu()) {
                if (ctDv.getMaDichVu().getMaDichVu().equals(dv.getMaDichVu())){
                    ctDv.setThanhTien(dv.getThanhTien());
                }    
            }
        }
        
        hoaDonThanhToanRepository.save(hoaDon);
    }
}
