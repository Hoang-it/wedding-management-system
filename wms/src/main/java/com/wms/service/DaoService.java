package com.wms.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wms.ValidationResponse;
import com.wms.dto.CaDTO;
import com.wms.dto.DichVuDTO;
import com.wms.dto.DoanhThuNgayDTO;
import com.wms.dto.HoaDonDTO;
import com.wms.dto.LoaiSanhDTO;
import com.wms.dto.MonAnDTO;
import com.wms.dto.NhomNguoiDungDTO;
import com.wms.dto.SanhDTO;
import com.wms.dto.TiecDTO;
import com.wms.dto.UserDTO;
import com.wms.entities.BaoCaoDoanhThu;
import com.wms.entities.Ca;
import com.wms.entities.ChiTietBaoCao;
import com.wms.entities.ChiTietDichVu;
import com.wms.entities.ChiTietMonAn;
import com.wms.entities.ChucNang;
import com.wms.entities.Sanh;
import com.wms.entities.DichVu;
import com.wms.entities.HoaDon;
import com.wms.entities.LoaiSanh;
import com.wms.entities.MonAn;
import com.wms.entities.NguoiDung;
import com.wms.entities.NhomNguoiDung;
import com.wms.entities.ThamSo;
import com.wms.entities.TiecCuoi;
import com.wms.repositories.BaoCaoDoanhThuRepository;
import com.wms.repositories.CaRepository;
import com.wms.repositories.ChiTietBaoCaoRepository;
import com.wms.repositories.ChucNangRepository;
import com.wms.repositories.DanhSachSanhRepository;
import com.wms.repositories.DichVuRepository;
import com.wms.repositories.DoanhThuThangRepository;
import com.wms.repositories.HoaDonThanhToanRepository;
import com.wms.repositories.LoaiSanhRepository;
import com.wms.repositories.MonAnRepository;
import com.wms.repositories.NguoiDungRepository;
import com.wms.repositories.NhomNguoiDungRepository;
import com.wms.repositories.SanhRepository;
import com.wms.repositories.ThamSoRepository;
import com.wms.repositories.TiecCuoiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DaoService {
    @Autowired
    BaoCaoDoanhThuRepository baoCaoDoanhThuRepository;

    @Autowired
    ChiTietBaoCaoRepository chiTietBaoCaoRepository;

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

    @Autowired
    NhomNguoiDungRepository nhomNguoiDungRepository;

    @Autowired
    ChucNangRepository chucNangRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    BaoCaoService baoCaoService;

    @Autowired
    SanhRepository sanhRepository;

    
    public List<LoaiSanhDTO> layDanhSachLoaiSanh(){
        List<LoaiSanhDTO> ds = new ArrayList<LoaiSanhDTO>();

        LoaiSanhDTO d = null;
        for (LoaiSanh data : loaiSanhRepository.findAll()) {
            d = new LoaiSanhDTO();
            d.setTenLoaiSanh(data.getTenLoaiSanh());
            d.setMaLoaiSanh(data.getMaLoaiSanh());
            d.setDonGiaBanToiThieu(data.getDonGiaBanToiThieu());
            ds.add(d);
        }
        return ds;
    }


    public void datSanhCuoi(SanhDTO sanhMoi){
        Sanh record = new Sanh();
        ThamSo soLuongMaDaCap = thamSoRepository.findById("maSanhDaCap").get();
        Long soLuong = Long.parseLong(soLuongMaDaCap.getGiaTri()) + 1;

        String index = soLuong < 10 ? "0" + soLuong.toString() : soLuong.toString();
        record.setMaSanh("SA" + index);
       
        record.setMaLoaiSanh(loaiSanhRepository.findById(sanhMoi.getLoaiSanh()).get());
        record.setTenSanh(sanhMoi.getTenSanh());
        record.setGhiChu(sanhMoi.getGhiChu());
        record.setSoLuongBanToiDa(sanhMoi.getSoLuongBanToiDa());
        soLuongMaDaCap.setGiaTri(soLuong.toString());

        thamSoRepository.save(soLuongMaDaCap);
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
        for (Sanh sanh : danhSachSanhRepository.findAll()) {
            data = new SanhDTO();
            data.setMaSanh(sanh.getMaSanh());
            data.setTenSanh(sanh.getTenSanh());
            data.setLoaiSanh(sanh.getMaLoaiSanh().getTenLoaiSanh());
            data.setDonGiaBanToiThieu(sanh.getMaLoaiSanh().getDonGiaBanToiThieu());
            data.setSoLuongBanToiDa(sanh.getSoLuongBanToiDa());
            dsSanh.add(data);
        }
        return dsSanh;
    }

    public List<SanhDTO> laySanh(String maSanh){
        List<SanhDTO> dsSanh = new ArrayList<>();
        SanhDTO data = null;
        Sanh sanh = danhSachSanhRepository.findById(maSanh).get();
        data = new SanhDTO();
        data.setMaSanh(sanh.getMaSanh());
        data.setTenSanh(sanh.getTenSanh());
        data.setLoaiSanh(sanh.getMaLoaiSanh().getTenLoaiSanh());
        data.setDonGiaBanToiThieu(sanh.getMaLoaiSanh().getDonGiaBanToiThieu());
        data.setSoLuongBanToiDa(sanh.getSoLuongBanToiDa());
        dsSanh.add(data);
    
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

    public List<MonAnDTO> layDanhSachMonAnTrongBuoiTiec(String maBuoiTiec){
        TiecCuoi tiec = tiecCuoiRepository.findById(maBuoiTiec).get();
        List<MonAnDTO> dsMonAn = new ArrayList<>();
        MonAnDTO data = null;
        for (ChiTietMonAn monAn : tiec.getChiTietMonAn()) {
            data = new MonAnDTO();
            data.setMaMonAn(monAn.getMaMonAn().getMaMonAn());
            data.setTenMonAn(monAn.getMaMonAn().getTenMonAn());
            data.setDonGia(monAn.getMaMonAn().getDonGia());
            
            dsMonAn.add(data);
        }
        return dsMonAn;
    }

    public List<DichVuDTO> layDanhSachDichVuTrongBuoiTiec(String maBuoiTiec){
        TiecCuoi tiec = tiecCuoiRepository.findById(maBuoiTiec).get();
        List<DichVuDTO> dsDichVu = new ArrayList<>();
        DichVuDTO data = null;
        for (ChiTietDichVu dichVu : tiec.getChiTietDichVu()){
            data = new DichVuDTO();
            data.setMaDichVu(dichVu.getMaDichVu().getMaDichVu());
            data.setTenDichVu(dichVu.getMaDichVu().getTenDichVu());
            data.setDonGia(dichVu.getMaDichVu().getDonGia());
            dsDichVu.add(data);
        }
        return dsDichVu;
    }

    public boolean datTiecCuoi(TiecDTO tiecCuoi, ValidationResponse res){
        TiecCuoi record = new TiecCuoi();
        HoaDon hoaDon = new HoaDon();  
        ThamSo soLuongMaTiecCuoiDaCap = thamSoRepository.findById("maHoaDonDaCap").get();
        ThamSo soLuongHoaDonCuoiDaCap = thamSoRepository.findById("maTiecCuoiDaCap").get();
        Long rowHD = Long.parseLong(soLuongHoaDonCuoiDaCap.getGiaTri()) + 1;       
        String indexHD = rowHD < 10 ? "0" + rowHD.toString() : rowHD.toString();
        Set<ChiTietMonAn> thucDon = new HashSet<>();
        Set<ChiTietDichVu> dsDichVu = new HashSet<>();
        Long rowTC = Long.parseLong(soLuongMaTiecCuoiDaCap.getGiaTri()) + 1; 
        String indexTC = rowTC < 10 ? "0" + rowTC.toString() : rowTC.toString();
        boolean choPhepDatTiec = true;
        HashMap<String, String> errorFields = new HashMap<>();
        List container = new ArrayList<>();

        List<TiecCuoi> dsTiecCuoiNgay = tiecCuoiRepository.findAllByNgayDaiTiec(tiecCuoi.getNgayDaiTiec());

        for (TiecCuoi tC : dsTiecCuoiNgay) {
            if (tiecCuoi.getCa().equals(tC.getMaCa().getMaCa())){
                if (tiecCuoi.getSanh().equals(tC.getMaSanh().getMaSanh())){
                    choPhepDatTiec = false;
                }
                
            }
        }

        if (choPhepDatTiec){
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

            soLuongHoaDonCuoiDaCap.setGiaTri(indexHD);
            soLuongMaTiecCuoiDaCap.setGiaTri(indexTC);

            thamSoRepository.save(soLuongHoaDonCuoiDaCap);
            thamSoRepository.save(soLuongMaTiecCuoiDaCap);
            tiecCuoiRepository.save(record);      
            hoaDonThanhToanRepository.save(hoaDon); 
            return choPhepDatTiec;
        }
        
        return choPhepDatTiec;
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

    public List<HoaDonDTO> layToanBoDanhSachHoaDon(){
        List<HoaDonDTO> dsHoaDon = new ArrayList<>();
        HoaDonDTO data = null;
        for (HoaDon hoaDon : hoaDonThanhToanRepository.findAll()) {
            data = new HoaDonDTO();
            data.setMaHoaDon(hoaDon.getMaHoaDon());
            data.setMaTiecCuoi(hoaDon.getMaTiecCuoi().getMaTiecCuoi());
            data.setTenCoDau(hoaDon.getMaTiecCuoi().getTenCoDau());
            data.setTenChuRe(hoaDon.getMaTiecCuoi().getTenChuRe());
            data.setNgayDaiTiec(hoaDon.getMaTiecCuoi().getNgayDaiTiec());
            if (hoaDon.getNgayThanhToan() != null){
                data.setNgayThanhToan(hoaDon.getNgayThanhToan().getNgayThanhToan());
            }
            data.setTongTienHoaDon(hoaDon.getTongTienHoaDon());            
            // data.setConLai(hoaDon.getConLai());

            dsHoaDon.add(data);
        }
        return dsHoaDon;
    }

    public List<TiecDTO> layTiecCuoi(String maTiecCuoi){
        List<TiecDTO> dsTiecCuoi = new ArrayList<>();
        TiecDTO data = null;
        TiecCuoi tiecCuoi = tiecCuoiRepository.findById(maTiecCuoi).get();
        data = new TiecDTO();
        data.setMaTiecCuoi(tiecCuoi.getMaTiecCuoi());
        data.setTenCoDau(tiecCuoi.getTenCoDau());
        data.setTenChuRe(tiecCuoi.getTenChuRe());
        data.setSanh(tiecCuoi.getMaSanh().getTenSanh());
        data.setNgayDaiTiec(tiecCuoi.getNgayDaiTiec());
        data.setGio(tiecCuoi.getMaCa().getGioBatDau());
        data.setSoLuongBan(tiecCuoi.getSoLuongBan());

        dsTiecCuoi.add(data);
        
        return dsTiecCuoi;
    }

    public void xoaTiecCuoi(String maTiecCuoi){
        TiecCuoi tiecCuoi = tiecCuoiRepository.findById(maTiecCuoi).get();
        if (tiecCuoi.getHoaDonThanhToan().getNgayThanhToan() == null){
            hoaDonThanhToanRepository.delete(tiecCuoi.getHoaDonThanhToan());            
                    
        }
        tiecCuoiRepository.deleteById(maTiecCuoi);
        
    }

    public void xoaHoaDon(String maTiecCuoi){
        TiecCuoi tiecCuoi = tiecCuoiRepository.findById(maTiecCuoi).get();        
        if (tiecCuoi.getHoaDonThanhToan().getNgayThanhToan() == null){
            hoaDonThanhToanRepository.delete(tiecCuoi.getHoaDonThanhToan());            
                    
        }
        tiecCuoiRepository.deleteById(maTiecCuoi);
    }

    public List<UserDTO> layToanBoDanhSachNguoiDung(){
        List<UserDTO> dsNguoiDung = new ArrayList<>();
        UserDTO data = null;
        ChucNang chucNang = null;
        
        for (NguoiDung nguoiDung : nguoiDungRepository.findAll()) {
            data = new UserDTO();  
            chucNang = nguoiDung.getMaNhom().getChucNang(); 
              
            data.setMaTaiKhoan(nguoiDung.getId());
            data.setTenTaiKhoan(nguoiDung.getTenDangNhap());
            data.setQuyen(chucNang.getTenChucNang());
            dsNguoiDung.add(data);
        }
        return dsNguoiDung;
    }

    public List<NhomNguoiDungDTO> layToanBoDanhSachNhomNguoiDung(){
        List<NhomNguoiDungDTO> dsNhomNguoiDung = new ArrayList<>();
        NhomNguoiDungDTO data = null;
       
        for (NhomNguoiDung nhomNguoiDung : nhomNguoiDungRepository.findAll()) {
            data = new NhomNguoiDungDTO();  
            data.setId(nhomNguoiDung.getId());
            data.setMaNhom(nhomNguoiDung.getMaNhom());
            // data.setDsNguoiDung(nhomNguoiDung.getDsNguoiDung());
            // data.setPhanQuyenIds(nhomNguoiDung.getPhanQuyenIds());
            data.setQuyen(nhomNguoiDung.getChucNang().getTenChucNang());
            data.setTenNhom(nhomNguoiDung.getTenNhom());
            dsNhomNguoiDung.add(data);
        }
        return dsNhomNguoiDung;
    }

    public List<UserDTO> layDanhSachNguoiDungTrongNhom(String maNhom){
        List<UserDTO> dsNguoiDung = new ArrayList<>();
        NhomNguoiDung nhom = nhomNguoiDungRepository.findByMaNhom(maNhom);
        UserDTO data = null;
       
        for (NguoiDung nguoiDung : nhom.getDsNguoiDung()) {
            data = new UserDTO();  
            data.setMaTaiKhoan(nguoiDung.getId());
            data.setTenTaiKhoan(nguoiDung.getTenDangNhap());
            dsNguoiDung.add(data);
        }
        return dsNguoiDung;
    }

    public void capNhatTaiKhoan(UserDTO model){
        
        
    }

    public void capNhatSanh(SanhDTO sanh){
        Sanh record = sanhRepository.findById(sanh.getMaSanh()).get();
        System.out.println("CẬP NHẬT SẢNH");
        record.setTenSanh(sanh.getTenSanh());
        record.setMaLoaiSanh(loaiSanhRepository.findById(sanh.getLoaiSanh()).get());
        record.setSoLuongBanToiDa(sanh.getSoLuongBanToiDa());
        
        sanhRepository.save(record);
    }

    public void capNhatNhomTaiKhoan(NhomNguoiDungDTO model){
        NhomNguoiDung nhom = nhomNguoiDungRepository.findByMaNhom(model.getMaNhom());
        nhom.setTenNhom(model.getTenNhom());
        nhom.setChucNang(chucNangRepository.findByTenChucNang(model.getQuyen()));
        nhomNguoiDungRepository.save(nhom);
    }

    public UserDTO layThongTinTaiKhoan(Long maTaiKhoan){
        UserDTO model = new UserDTO();
        NguoiDung data = nguoiDungRepository.findById(maTaiKhoan).get();
        ChucNang chucNang = data.getMaNhom().getChucNang();
        

        model.setMaTaiKhoan(data.getId());
        model.setTenTaiKhoan(data.getTenDangNhap());
        model.setQuyen(chucNang.getTenChucNang());
        return model;
    }

    public ThamSo layQuyDinhPhat(){
        return thamSoRepository.findById("KiemTraNgayThanhToan").get();
    
    }

    public void capNhatQuyDinh(ThamSo thamSo){
        ThamSo record = thamSoRepository.findById(thamSo.getTenThamSo()).get();
        
        record.setGiaTri(thamSo.getGiaTri());
        thamSoRepository.save(record);
    }

    public BigDecimal layDoanhThuThang(String thang, String nam){
        BaoCaoDoanhThu doanhThuThang = baoCaoDoanhThuRepository.findById("BC" + thang + nam).get();
        
        return doanhThuThang.getTongDoanhThu();
    }

    public List<DoanhThuNgayDTO> layDanhSachDoanhThuNgay(String thang, String nam){
        List<DoanhThuNgayDTO> dsDoanhThuNgay = new ArrayList<>();
        BaoCaoDoanhThu doanhThuThang = baoCaoDoanhThuRepository.findById("BC" + thang + nam).get();
        DoanhThuNgayDTO temp = null;
        BigDecimal tile;
        for (ChiTietBaoCao doanhThuNgay : doanhThuThang.getChiTietBaoCao()) {
            temp = new DoanhThuNgayDTO();
            tile = doanhThuNgay.getDoanhThu().divide(doanhThuThang.getTongDoanhThu(), 4, RoundingMode.HALF_UP);

            temp.setDoanhThuNgay(doanhThuNgay.getDoanhThu().doubleValue());
            temp.setSoLuongTiecCuoi(doanhThuNgay.getSoLuongTiecCuoi());
            temp.setTiLe(tile.doubleValue());
            temp.setNgay(doanhThuNgay.getNgayThanhToan().getDayOfMonth());
            dsDoanhThuNgay.add(temp);

            doanhThuNgay.setTiLe(tile.doubleValue());
            chiTietBaoCaoRepository.save(doanhThuNgay);
            
        }
        return dsDoanhThuNgay;
    }

    public DichVuDTO layThongTinDichVu(String maDichVU){
        DichVuDTO model = new DichVuDTO();
        DichVu data = dichVuRepository.findById(maDichVU).get();
        
        model.setMaDichVu(data.getMaDichVu());
        model.setTenDichVu(data.getTenDichVu());
        model.setDonGia(data.getDonGia());
        return model;
    }

    public void capNhatThongTinDichVu(DichVuDTO dichVu){
        DichVu record = dichVuRepository.findById(dichVu.getMaDichVu()).get();
        
        record.setTenDichVu(dichVu.getTenDichVu());
        record.setDonGia(dichVu.getDonGia());
        dichVuRepository.save(record);
    }

    public MonAnDTO layThongTinMonAn(String maMonAn){
        MonAnDTO model = new MonAnDTO();
        MonAn data = monAnRepository.findById(maMonAn).get();
        
        model.setMaMonAn(data.getMaMonAn());
        model.setTenMonAn(data.getTenMonAn());
        model.setDonGia(data.getDonGia());
        return model;
    }

    public void capNhatThongTinMonAn(MonAnDTO monAn){
        MonAn record = monAnRepository.findById(monAn.getMaMonAn()).get();
        
        record.setMaMonAn(monAn.getMaMonAn());
        record.setTenMonAn(monAn.getTenMonAn());
        record.setDonGia(monAn.getDonGia());
        monAnRepository.save(record);
    }

    public LoaiSanhDTO layThongTinLoaiSanh(String maLoaiSanh){
        LoaiSanhDTO model = new LoaiSanhDTO();
        LoaiSanh data = loaiSanhRepository.findById(maLoaiSanh).get();
        
        model.setMaLoaiSanh(data.getMaLoaiSanh());
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
        ThamSo soLuongMaDaCap = thamSoRepository.findById("maLoaiSanhDaCap").get();
        Long soLuong = Long.parseLong(soLuongMaDaCap.getGiaTri()) + 1;
        String index = soLuong < 10 ? "0" + soLuong.toString() : soLuong.toString();

        record.setMaLoaiSanh("LS" + soLuong);
        record.setTenLoaiSanh(sanh.getTenLoaiSanh());
        record.setDonGiaBanToiThieu(sanh.getDonGiaBanToiThieu());    
        soLuongMaDaCap.setGiaTri(soLuong.toString());
        
        thamSoRepository.save(soLuongMaDaCap);
        loaiSanhRepository.save(record);
    }

    public void xoaThongTinLoaiSanh(String maLoaiSanh){
        loaiSanhRepository.deleteById(maLoaiSanh);
    }

    public void xoaThongTinSanh(String maSanh){
        sanhRepository.deleteById(maSanh);
    }

    public void xoaThongTinCa(String maCa){
        caRepository.deleteById(maCa);
    }

    public void xoaThongTinMonAn(String maMonAn){
        monAnRepository.deleteById(maMonAn);
    }

    public void xoaThongTinDichVu(String maDichVu){
        dichVuRepository.deleteById(maDichVu);
    }

    public void capNhatThongTinCa(CaDTO ca){
        Ca record = caRepository.findById(ca.getMaCa()).get();
        record.setTenCa(ca.getTenCa());
        record.setGioBatDau(ca.getGioBatDau());
        record.setGioKetThuc(ca.getGioKetThuc());
        caRepository.save(record);
    }

    public void themThongTinCa(CaDTO ca){
        Ca record = new Ca();
        ThamSo soLuongMaDaCap = thamSoRepository.findById("maCaDaCap").get();
        Long soLuong = Long.parseLong(soLuongMaDaCap.getGiaTri()) + 1;
        String index = null;

        if (soLuong < 10){
            index = "0" + soLuong;
        } else{
            index = "" + soLuong;
        }
        String maCa = "CA" + index;
        System.out.println("Ma CA" + maCa);
        record.setMaCa(maCa);
        record.setTenCa(ca.getTenCa());
        record.setGioBatDau(ca.getGioBatDau());
        record.setGioKetThuc(ca.getGioKetThuc());

        soLuongMaDaCap.setGiaTri(soLuong.toString());

        thamSoRepository.save(soLuongMaDaCap);
        caRepository.save(record);
    }

    public void themThongDichVu(DichVuDTO dv){
        DichVu record = new DichVu();
        ThamSo soLuongMaDaCap = thamSoRepository.findById("maDichVuDaCap").get();
        Long soLuong = Long.parseLong(soLuongMaDaCap.getGiaTri()) + 1;
        String index = null;

        if (soLuong < 10){
            index = "0" + soLuong;
        } else{
            index = "" + soLuong;
        }
        String maDV = "DV" + index;
        System.out.println("Ma DV" + maDV);
        record.setMaDichVu(maDV);
        record.setTenDichVu(dv.getTenDichVu());
        record.setDonGia(dv.getDonGia());

        soLuongMaDaCap.setGiaTri(soLuong.toString());
        
        dichVuRepository.save(record);
        thamSoRepository.save(soLuongMaDaCap);
    }

    public void themThongMonAn(MonAnDTO monAn){
        MonAn record = new MonAn();
        ThamSo soLuongMaDaCap = thamSoRepository.findById("maMonAnDaCap").get();
        Long soLuong = Long.parseLong(soLuongMaDaCap.getGiaTri()) + 1;
        String index = null;

        if (soLuong < 10){
            index = "00" + soLuong;
        } else{
            if (soLuong < 100){
                index = "0" + soLuong;
            } else{
                index = "" + soLuong;
            }
            
        }
        String maMonAn = "M" + index;
        System.out.println("Ma MonAn" + maMonAn);
        record.setMaMonAn(maMonAn);
        record.setTenMonAn(monAn.getTenMonAn());
        record.setDonGia(monAn.getDonGia());
        
        soLuongMaDaCap.setGiaTri(soLuong.toString());
        
        monAnRepository.save(record);
        thamSoRepository.save(soLuongMaDaCap);
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
        System.out.println("Phần trăm phạt : " + (thamSoRepository.findById("TiLePhanTramPhat").get()).getGiaTri());
        BigDecimal mucPhat = new BigDecimal(thamSoRepository.findById("TiLePhanTramPhat").get().getGiaTri());
        
        
        data.setTenCoDau(tiecCuoi.getTenCoDau());
        data.setTenChuRe(tiecCuoi.getTenChuRe());
        data.setSoLuongBan(tiecCuoi.getSoLuongBan());
        data.setNgayDaiTiec(tiecCuoi.getNgayDaiTiec());
        data.setSoLuongBanDuTru(tiecCuoi.getSoLuongBanDuTru());
        data.setTienDatCoc(tiecCuoi.getTienDatCoc());
        if (tiecCuoi.getHoaDonThanhToan().getNgayThanhToan() != null){
            data.setNgayThanhToan(tiecCuoi.getHoaDonThanhToan().getNgayThanhToan().getNgayThanhToan());
            data.setDaThanhToan(true);
        }else {
            data.setNgayThanhToan(LocalDate.now());
            data.setDaThanhToan(false);
            
        }
        ngayTreHan = ChronoUnit.DAYS.between(tiecCuoi.getNgayDaiTiec(), data.getNgayThanhToan());    
        data.setNgayTreHan(ngayTreHan);
        
        System.out.println("Phần trăm phạt : " + mucPhat.doubleValue());
        System.out.println("ngày trễ hạn : " + ngayTreHan);
        System.out.println("Tiền phạt : " + mucPhat.multiply(new BigDecimal(ngayTreHan)));
        
        if ("true".equals(apDungQuyDinh.getGiaTri().toLowerCase())){
            mucPhat = mucPhat.multiply(new BigDecimal(ngayTreHan));
        } else{
            mucPhat = new BigDecimal(0);
        }
        
        for (ChiTietDichVu ctDichVu : tiecCuoi.getChiTietDichVu()) {
            dichVu = new DichVuDTO();
            dichVu.setMaDichVu(ctDichVu.getMaDichVu().getMaDichVu());
            dichVu.setTenDichVu(ctDichVu.getMaDichVu().getTenDichVu());
            dichVu.setSoLuong(ctDichVu.getSoLuong());
            dichVu.setDonGia(ctDichVu.getDonGiaDichVu());
                
            dichVu.setThanhTien(ctDichVu.getDonGiaDichVu().multiply(new BigDecimal(ctDichVu.getSoLuong())));
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
        data.setTienPhat(hoaDonService.tinhTienPhat(data.getTongTienBan(), data.getTongTienDichVu(), mucPhat));
        data.setTongTienHoaDon(hoaDonService.tinhTongTienHoaDon(data.getTongTienBan(), data.getTongTienDichVu(), mucPhat));
        data.setTongTienMonAn(data.getDonGiaBan());
        data.setConLai(hoaDonService.tinhTienConLai(data.getTongTienHoaDon(), tiecCuoi.getTienDatCoc()));
        
        return data;
    }

    public HoaDonDTO layThongTinTiecCuoi(String maTiecCuoi){
        TiecCuoi tiecCuoi = tiecCuoiRepository.findById(maTiecCuoi).get();

        HoaDonDTO data = new HoaDonDTO();
        List<DichVuDTO> dsDichVu = new ArrayList<>();
        List<MonAnDTO> dsMonAn = new ArrayList<>();
        Long ngayTreHan = 0l;
        DichVuDTO dichVu = null;
        MonAnDTO monAn = null;
        BigDecimal tienPhat = new BigDecimal("1.0");
        
        ThamSo apDungQuyDinh = thamSoRepository.findById("KiemTraNgayThanhToan").get();
        System.out.println("Phần trăm phạt : " + (thamSoRepository.findById("TiLePhanTramPhat").get()).getGiaTri());
        BigDecimal mucPhat = new BigDecimal(thamSoRepository.findById("TiLePhanTramPhat").get().getGiaTri());
        
        
        data.setTenCoDau(tiecCuoi.getTenCoDau());
        data.setTenChuRe(tiecCuoi.getTenChuRe());
        data.setSoLuongBan(tiecCuoi.getSoLuongBan());
        data.setNgayDaiTiec(tiecCuoi.getNgayDaiTiec());
        data.setSoLuongBanDuTru(tiecCuoi.getSoLuongBanDuTru());
        data.setSoDienThoai(tiecCuoi.getDienThoai());
        data.setCa(tiecCuoi.getMaCa().getMaCa());
        data.setSanh(tiecCuoi.getMaSanh().getMaSanh());

        data.setTienDatCoc(tiecCuoi.getTienDatCoc());
        data.setNgayThanhToan(LocalDate.now());

        ngayTreHan = ChronoUnit.DAYS.between(tiecCuoi.getNgayDaiTiec(), data.getNgayThanhToan());
        data.setNgayTreHan(ngayTreHan);
        
        System.out.println("Phần trăm phạt : " + mucPhat.doubleValue());
        System.out.println("ngày trễ hạn : " + ngayTreHan);
        System.out.println("Tiền phạt : " + mucPhat.multiply(new BigDecimal(ngayTreHan)));

        if ("true".equals(apDungQuyDinh.getGiaTri().toLowerCase())){
            mucPhat = mucPhat.multiply(new BigDecimal(ngayTreHan));
        } else{
            mucPhat = new BigDecimal(0);
        }
        
        for (ChiTietDichVu ctDichVu : tiecCuoi.getChiTietDichVu()) {
            dichVu = new DichVuDTO();
            dichVu.setMaDichVu(ctDichVu.getMaDichVu().getMaDichVu());
            dichVu.setTenDichVu(ctDichVu.getMaDichVu().getTenDichVu());
            dichVu.setSoLuong(ctDichVu.getSoLuong());
            dichVu.setDonGia(ctDichVu.getDonGiaDichVu());
                
            dichVu.setThanhTien(ctDichVu.getDonGiaDichVu().multiply(new BigDecimal(ctDichVu.getSoLuong())));
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
        data.setTongTienHoaDon(hoaDonService.tinhTongTienHoaDon(data.getTongTienBan(), data.getTongTienDichVu(), mucPhat));
        data.setTongTienMonAn(data.getDonGiaBan());
        data.setConLai(hoaDonService.tinhTienConLai(data.getTongTienHoaDon(), tiecCuoi.getTienDatCoc()));
        
        return data;
    }


    public void lapHoaDon(HoaDonDTO data){
        TiecCuoi tiecCuoi = tiecCuoiRepository.findById(data.getMaTiecCuoi()).get();
        HoaDon hoaDon = tiecCuoi.getHoaDonThanhToan();  
        ChiTietBaoCao doanhThuNgay = null;
        BaoCaoDoanhThu doanhThuThang = null;
        String maBCT = "BC" + data.getNgayThanhToan().getMonthValue() + data.getNgayThanhToan().getYear();
        Set<ChiTietBaoCao> dsDoanhThuNgay = null;
        Set<HoaDon> dsHoaDon = null;
        System.out.println(maBCT);

        try {
            baoCaoDoanhThuRepository.findById(maBCT).get();
            
        } catch (Exception e) {
            //TODO: handle exception
            dsDoanhThuNgay = new HashSet<>();
            doanhThuThang = new BaoCaoDoanhThu();
            doanhThuThang.setMaBaoCaoDoanhThu(maBCT);
            doanhThuThang.setNam(data.getNgayThanhToan().getYear());
            doanhThuThang.setThang(data.getNgayThanhToan().getMonthValue());
            doanhThuThang.setChiTietBaoCao(dsDoanhThuNgay);
            doanhThuThang.setTongDoanhThu(new BigDecimal("0"));
            baoCaoDoanhThuRepository.save(doanhThuThang);
        }
        
        doanhThuThang = baoCaoDoanhThuRepository.findById(maBCT).get();
        dsDoanhThuNgay = doanhThuThang.getChiTietBaoCao();

        try {
            chiTietBaoCaoRepository.findById(data.getNgayThanhToan()).get();
            System.out.println("Thanh cong");
        } catch (Exception e) {
            doanhThuNgay = new ChiTietBaoCao();
            dsHoaDon = new HashSet<>();
            doanhThuNgay.setNgayThanhToan(data.getNgayThanhToan());
            doanhThuNgay.setBaoCaoDoanhThu(doanhThuThang);
            doanhThuNgay.setSoLuongTiecCuoi(0);
            doanhThuNgay.setDsHoaDon(dsHoaDon);
            doanhThuNgay.setDoanhThu(new BigDecimal("0"));
            doanhThuNgay.setTiLe(0);
            chiTietBaoCaoRepository.save(doanhThuNgay);
            System.out.println("Khong Thanh cong");
        }

        doanhThuNgay =  chiTietBaoCaoRepository.findById(data.getNgayThanhToan()).get();
        dsHoaDon = doanhThuNgay.getDsHoaDon();

        hoaDon.setTienDatCoc(data.getTienDatCoc());        
        hoaDon.setDonGiaBan(data.getDonGiaBan());
        hoaDon.setNgayThanhToan(doanhThuNgay);
        hoaDon.setTongTienBan(data.getTongTienBan());
        hoaDon.setTongTienDichVu(data.getTongTienDichVu());
        hoaDon.setTongTienHoaDon(data.getTongTienHoaDon());
        hoaDon.setConLai(data.getConLai());
        hoaDon = hoaDonThanhToanRepository.save(hoaDon);
        dsHoaDon.add(hoaDon);        

        doanhThuNgay.setDoanhThu(baoCaoService.tinhDoanhThuTheoNgayTrongThang(dsHoaDon));
        doanhThuNgay.setSoLuongTiecCuoi(baoCaoService.tinhTongSoLuongTiecCuoiTrongThang(dsHoaDon));
        doanhThuNgay.setDsHoaDon(dsHoaDon);
        doanhThuNgay = chiTietBaoCaoRepository.save(doanhThuNgay);

        dsDoanhThuNgay.add(doanhThuNgay);
        doanhThuThang.setChiTietBaoCao(dsDoanhThuNgay);
        doanhThuThang.setTongDoanhThu(baoCaoService.tinhTongDoanhThuThang(doanhThuThang.getChiTietBaoCao()));
        doanhThuThang = baoCaoDoanhThuRepository.save(doanhThuThang);

        doanhThuNgay.setTiLe(baoCaoService.tinhTyLeDoanhThuTheoNgayTrongThang(doanhThuThang.getTongDoanhThu(), doanhThuNgay.getDoanhThu()));
        chiTietBaoCaoRepository.save(doanhThuNgay);
        for (ChiTietDichVu ctDv : tiecCuoi.getChiTietDichVu()) {
            for (DichVuDTO dv : data.getDichVu()) {
                if (ctDv.getMaDichVu().getMaDichVu().equals(dv.getMaDichVu())){
                    ctDv.setThanhTien(dv.getThanhTien());
                }    
            }
        }
        
        tiecCuoiRepository.save(tiecCuoi);
        
    }
}
