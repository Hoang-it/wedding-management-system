package com.wms;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wms.entities.Ca;
import com.wms.entities.ChiTietDichVu;
import com.wms.entities.ChiTietMonAn;
import com.wms.entities.ChucNang;
import com.wms.entities.DanhSachSanh;
import com.wms.entities.DichVu;
import com.wms.entities.DoanhThuThang;
import com.wms.entities.HoaDonThanhToan;
import com.wms.entities.LoaiSanh;
import com.wms.entities.MonAn;
import com.wms.entities.NguoiDung;
import com.wms.entities.NhomNguoiDung;
import com.wms.entities.PhanQuyen;
import com.wms.entities.ThamSo;
import com.wms.entities.TiecCuoi;
import com.wms.repositories.CaRepository;
import com.wms.repositories.ChucNangRepository;
import com.wms.repositories.DanhSachSanhRepository;
import com.wms.repositories.DichVuRepository;
import com.wms.repositories.DoanhThuThangRepository;
import com.wms.repositories.HoaDonThanhToanRepository;
import com.wms.repositories.LoaiSanhRepository;
import com.wms.repositories.MonAnRepository;
import com.wms.repositories.NguoiDungRepository;
import com.wms.repositories.NhomNguoiDungRepository;
import com.wms.repositories.PhanQuyenRepository;
import com.wms.repositories.ThamSoRepository;
import com.wms.repositories.TiecCuoiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DefaultData implements CommandLineRunner{
    @Autowired
    private ChucNangRepository chucNangRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private NhomNguoiDungRepository nhomNguoiDungRepository;

    @Autowired
    private PhanQuyenRepository phanQuyenRepository;

    @Autowired
    private CaRepository caRepository;

    @Autowired
    private TiecCuoiRepository tiecCuoiRepository;

    @Autowired
    private DanhSachSanhRepository danhSachSanhRepository;

    @Autowired
    private LoaiSanhRepository loaiSanhRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MonAnRepository monAnRepository;

    @Autowired
    private DichVuRepository dichVuRepository;

    @Autowired
    private HoaDonThanhToanRepository hoaDonThanhToanRepository;

    @Autowired
    private DoanhThuThangRepository doanhThuThangRepository;

    @Autowired
    private ThamSoRepository thamSoRepository;

    private void createDefaultCacLoaiChucNang(){
        List<ChucNang> cacLoaiChucNang = new ArrayList<>();
        cacLoaiChucNang.add(new ChucNang("CN1", "QuanTriVien", ""));
        cacLoaiChucNang.add(new ChucNang("CN2", "BQL", ""));
        cacLoaiChucNang.add(new ChucNang("CN3", "NhanVien", ""));
        cacLoaiChucNang.add(new ChucNang("CN4", "Khac", ""));
        chucNangRepository.saveAll(cacLoaiChucNang);
        System.out.println("Đã tạo thành công các loại chức năng!");
    }

    private void createDefaultDanhSachNguoiDung(){
        List<NguoiDung> cacNguoiDung = new ArrayList<>();
        cacNguoiDung.add(new NguoiDung("user1", passwordEncoder.encode("1"), nhomNguoiDungRepository.findByMaNhom("NND1")));
        cacNguoiDung.add(new NguoiDung("user2", passwordEncoder.encode("1"), nhomNguoiDungRepository.findByMaNhom("NND2")));
        cacNguoiDung.add(new NguoiDung("user3", passwordEncoder.encode("1"), nhomNguoiDungRepository.findByMaNhom("NND3")));
        cacNguoiDung.add(new NguoiDung("user4", passwordEncoder.encode("1"), nhomNguoiDungRepository.findByMaNhom("NND4")));
        nguoiDungRepository.saveAll(cacNguoiDung);
        System.out.println("Đã tạo thành công danh sách người dùng!");
    }

    private void createDefaultCacNhomNguoiDung(){
        List<NhomNguoiDung> cacNhomNguoiDung = new ArrayList<>();
        cacNhomNguoiDung.add(new NhomNguoiDung("NND1", "NhomQuanLy"));
        cacNhomNguoiDung.add(new NhomNguoiDung("NND2", "NhomQuanTri"));
        cacNhomNguoiDung.add(new NhomNguoiDung("NND3", "NhomNhanVien"));
        cacNhomNguoiDung.add(new NhomNguoiDung("NND4", "NhomTuDo"));
        nhomNguoiDungRepository.saveAll(cacNhomNguoiDung);
        System.out.println("Đã tạo thành công các nhóm người dùng!");
    }

    private void createDefaultPhanQuyen(){      
        List<PhanQuyen> cacQuyen = new ArrayList<>();
        cacQuyen.add(new PhanQuyen(nhomNguoiDungRepository.findByMaNhom("NND1"), chucNangRepository.findByMaChucNang("CN2")));
        cacQuyen.add(new PhanQuyen(nhomNguoiDungRepository.findByMaNhom("NND2"), chucNangRepository.findByMaChucNang("CN1")));
        cacQuyen.add(new PhanQuyen(nhomNguoiDungRepository.findByMaNhom("NND3"), chucNangRepository.findByMaChucNang("CN3")));
        cacQuyen.add(new PhanQuyen(nhomNguoiDungRepository.findByMaNhom("NND4"), chucNangRepository.findByMaChucNang("CN4")));
        phanQuyenRepository.saveAll(cacQuyen);
        System.out.println("Đã tạo thành công phân quyền");
    }

    
    private void createDefaultDanhSachCa(){
        List<Ca> dsCA = new ArrayList<>();
        dsCA.add(new Ca("CA01", "SANG", LocalTime.of(8,0,0), LocalTime.of(12,0,0)));
        dsCA.add(new Ca("CA02", "CHIEU", LocalTime.of(13,0,0), LocalTime.of(18,0,0)));
       
        caRepository.saveAll(dsCA);
        System.out.println("Đã tạo thành công ds CA");
    }

    private void createDefaultLoaiSanh(){
        List<LoaiSanh> dsLoaiSanh = new ArrayList<>();
        dsLoaiSanh.add(new LoaiSanh("LS01", "A", new BigDecimal("1000000.00")));
        dsLoaiSanh.add(new LoaiSanh("LS02", "B", new BigDecimal("1100000.00")));
        dsLoaiSanh.add(new LoaiSanh("LS03", "C", new BigDecimal("1200000.00")));
        dsLoaiSanh.add(new LoaiSanh("LS04", "D", new BigDecimal("1400000.00")));
        dsLoaiSanh.add(new LoaiSanh("LS05", "E", new BigDecimal("1600000.00")));
        
       
        loaiSanhRepository.saveAll(dsLoaiSanh);
        System.out.println("Đã tạo thành công ds Sảnh");
    }

    private void createDefaultDanhSachSanh(){
        List<DanhSachSanh> dsSanh = new ArrayList<>();
        dsSanh.add(new DanhSachSanh("SA01", "SANG Alpha", 100l, "", loaiSanhRepository.findById("LS01").get()));
        dsSanh.add(new DanhSachSanh("SA02", "SANG Alpha", 100l, "", loaiSanhRepository.findById("LS01").get()));
        dsSanh.add(new DanhSachSanh("SA03", "SANG Alpha", 100l, "", loaiSanhRepository.findById("LS01").get()));
       
        danhSachSanhRepository.saveAll(dsSanh);
        System.out.println("Đã tạo thành công ds Sảnh");
    }

    private void createDefaultDanhSachMonAn(){
        List<MonAn> dsMonAn = new ArrayList<>();
        dsMonAn.add(new MonAn("MA00", "Thit heo 00", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA01", "Thit heo 01", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA02", "Thit heo 02", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA03", "Thit heo 03", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA04", "Thit heo 04", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA05", "Thit heo 05", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA07", "Thit heo 07", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA06", "Thit heo 06", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA08", "Thit heo 08", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA09", "Thit heo 09", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA10", "Thit heo 10", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA11", "Thit heo 11", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA12", "Thit heo 12", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA13", "Thit heo 13", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA14", "Thit heo 14", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA15", "Thit heo 15", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA16", "Thit heo 16", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA17", "Thit heo 17", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA18", "Thit heo 18", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA19", "Thit heo 19", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA20", "Thit heo 20", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA21", "Thit heo 21", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA22", "Thit heo 22", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA23", "Thit heo 23", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA24", "Thit heo 24", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA25", "Thit heo 25", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA26", "Thit heo 26", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA27", "Thit heo 27", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA28", "Thit heo 28", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA29", "Thit heo 29", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA30", "Thit heo 30", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA31", "Thit heo 31", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA32", "Thit heo 32", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA33", "Thit heo 33", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA34", "Thit heo 34", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA35", "Thit heo 35", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA36", "Thit heo 36", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA37", "Thit heo 37", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA38", "Thit heo 38", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA39", "Thit heo 39", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA40", "Thit heo 40", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA41", "Thit heo 41", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA42", "Thit heo 42", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA43", "Thit heo 43", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA44", "Thit heo 44", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA45", "Thit heo 45", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA46", "Thit heo 46", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA47", "Thit heo 47", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA48", "Thit heo 48", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA49", "Thit heo 49", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA50", "Thit heo 50", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA51", "Thit heo 51", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA52", "Thit heo 52", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA53", "Thit heo 53", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA54", "Thit heo 54", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA55", "Thit heo 55", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA56", "Thit heo 56", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA57", "Thit heo 57", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA58", "Thit heo 58", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA59", "Thit heo 59", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA60", "Thit heo 60", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA61", "Thit heo 61", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA62", "Thit heo 62", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA63", "Thit heo 63", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA64", "Thit heo 64", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA65", "Thit heo 65", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA66", "Thit heo 66", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA67", "Thit heo 67", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA68", "Thit heo 68", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA69", "Thit heo 69", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA70", "Thit heo 70", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA71", "Thit heo 71", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA72", "Thit heo 72", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA73", "Thit heo 73", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA74", "Thit heo 74", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA75", "Thit heo 75", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA76", "Thit heo 76", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA77", "Thit heo 77", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA78", "Thit heo 78", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA79", "Thit heo 79", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA80", "Thit heo 80", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA81", "Thit heo 81", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA82", "Thit heo 82", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA83", "Thit heo 83", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA84", "Thit heo 84", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA85", "Thit heo 85", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA86", "Thit heo 86", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA87", "Thit heo 87", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA88", "Thit heo 88", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA89", "Thit heo 89", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA90", "Thit heo 90", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA91", "Thit heo 91", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA92", "Thit heo 92", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA93", "Thit heo 93", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA94", "Thit heo 94", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA95", "Thit heo 95", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA96", "Thit heo 96", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA97", "Thit heo 97", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA98", "Thit heo 98", new BigDecimal("100000")));
        dsMonAn.add(new MonAn("MA99", "Thit heo 99", new BigDecimal("100000")));
        
       
        monAnRepository.saveAll(dsMonAn);
        System.out.println("Đã tạo thành công ds món ăn");
    }

    private void createDefaultDanhSachDichVu(){
        List<DichVu> dsDichVu = new ArrayList<>();
        dsDichVu.add(new DichVu("DV00", "Dich Vu 1", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV01", "Dich Vu 2", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV02", "Dich Vu 3", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV03", "Dich Vu 4", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV04", "Dich Vu 5", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV05", "Dich Vu 6", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV06", "Dich Vu 7", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV07", "Dich Vu 8", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV08", "Dich Vu 9", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV09", "Dich Vu 10", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV10", "Dich Vu 11", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV11", "Dich Vu 12", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV12", "Dich Vu 13", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV13", "Dich Vu 14", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV14", "Dich Vu 15", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV15", "Dich Vu 16", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV16", "Dich Vu 17", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV17", "Dich Vu 18", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV18", "Dich Vu 19", new BigDecimal("120000")));
        dsDichVu.add(new DichVu("DV19", "Dich Vu 20", new BigDecimal("120000")));
       
        dichVuRepository.saveAll(dsDichVu);
        System.out.println("Đã tạo thành công ds dịch vụ");
    }

    private void createDefaultTiec(){
        List<TiecCuoi> dsTiec = new ArrayList<>();
        Set<ChiTietMonAn> thucDon = new HashSet<ChiTietMonAn>();
        Set<ChiTietDichVu> dichVu = new HashSet<ChiTietDichVu>();

        
        dsTiec.add(new TiecCuoi("TC01", "Nam", "Linh", "0392661419", LocalDate.of(2021, 12, 4), new BigDecimal("1000000"), 100l, 10l, caRepository.findById("CA01").get(), danhSachSanhRepository.findById("SA01").get()));
        
        tiecCuoiRepository.saveAll(dsTiec);

        TiecCuoi tiec1 = tiecCuoiRepository.findById("TC01").get();

        thucDon.add(new ChiTietMonAn(tiec1, monAnRepository.findById("MA01").get(), ""));
        thucDon.add(new ChiTietMonAn(tiec1, monAnRepository.findById("MA02").get(), ""));
        thucDon.add(new ChiTietMonAn(tiec1, monAnRepository.findById("MA03").get(), ""));
        tiec1.setChiTietMonAn(thucDon);

        dichVu.add(new ChiTietDichVu(tiec1, dichVuRepository.findById("DV01").get(), 10l, new BigDecimal(100)));
        dichVu.add(new ChiTietDichVu(tiec1, dichVuRepository.findById("DV02").get(), 10l, new BigDecimal(100)));
        dichVu.add(new ChiTietDichVu(tiec1, dichVuRepository.findById("DV03").get(), 10l, new BigDecimal(100)));
        tiec1.setChiTietDichVu(dichVu);
        
        tiecCuoiRepository.save(tiec1);        
        System.out.println("Đã tạo thành công ds tiệc");
    }

    private void createDefaultHoaDonThanhToan(){
        Set<HoaDonThanhToan> hoaDonThanhToan = new HashSet<HoaDonThanhToan>();

        hoaDonThanhToan.add(new HoaDonThanhToan("HD01", LocalDate.of(2021, 12, 1), new BigDecimal("10000"), 
                new BigDecimal("10000"),new BigDecimal("10000"),new BigDecimal("10000"),new BigDecimal("10000"),
                 tiecCuoiRepository.findById("TC01").get(), doanhThuThangRepository.findById(1l).get() ));
        
        hoaDonThanhToanRepository.saveAll(hoaDonThanhToan);
        //chiTietMonAnRepository.saveAll(thucDon);
        System.out.println("Đã tạo thành công ds hoa don");
    }

    private void createDefaultDoanhThuThang(){
        Set<DoanhThuThang> soLieu = new HashSet<DoanhThuThang>();

        soLieu.add(new DoanhThuThang("12", "2021"));
        soLieu.add(new DoanhThuThang("11", "2021"));
        
        doanhThuThangRepository.saveAll(soLieu);
        //chiTietMonAnRepository.saveAll(thucDon);
        System.out.println("Đã tạo thành công ds hoa don");
    }

    private void createDefaultThamSo(){
        Set<ThamSo> data = new HashSet<ThamSo>();

        data.add(new ThamSo("KiemTraNgayThanhToan", "true"));
        data.add(new ThamSo("TiLePhanTramPhat", "0.1"));
        
        thamSoRepository.saveAll(data);
        //chiTietMonAnRepository.saveAll(thucDon);
        System.out.println("Đã tạo thành công ds hoa don");
    }

    
    @Override
    public void run(String... args) throws Exception {
        // TODO Tạo data default cho hệ thống
        // System.out.println("Genarate default data");


        // createDefaultCacLoaiChucNang();
        // createDefaultCacNhomNguoiDung();
        // createDefaultPhanQuyen();
        // createDefaultDanhSachNguoiDung();
  
        // createDefaultDanhSachCa();

        // createDefaultLoaiSanh();
        // createDefaultDanhSachSanh();

        // createDefaultDanhSachMonAn();

        // createDefaultDanhSachDichVu();
        // createDefaultTiec();
        // createDefaultDoanhThuThang();
        // createDefaultHoaDonThanhToan();
        // createDefaultThamSo();

        // NguoiDung nguoiDung = nguoiDungRepository.findByTenNguoiDung("user1");
        // for (PhanQuyen phanQuyen : nguoiDung.getMaNhom().getPhanQuyenIds()) {
        //     System.out.println(phanQuyen.getMaChucNang().getTenChucNang());    
        // }
    }
    
}
