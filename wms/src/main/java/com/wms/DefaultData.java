package com.wms;

import java.util.ArrayList;
import java.util.List;

import com.wms.entities.ChucNang;
import com.wms.entities.NguoiDung;
import com.wms.entities.NhomNguoiDung;
import com.wms.entities.PhanQuyen;
import com.wms.repositories.ChucNangRepository;
import com.wms.repositories.NguoiDungRepository;
import com.wms.repositories.NhomNguoiDungRepository;
import com.wms.repositories.PhanQuyenRepository;

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
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("Genarate default data");
        nguoiDungRepository.deleteAll();
        phanQuyenRepository.deleteAll();
        chucNangRepository.deleteAll();;
        nhomNguoiDungRepository.deleteAll();
        

        List<ChucNang> cacLoaiChucNang = new ArrayList<>();
        cacLoaiChucNang.add(new ChucNang("CN1", "QuanTriVien", ""));
        cacLoaiChucNang.add(new ChucNang("CN2", "BQL", ""));
        cacLoaiChucNang.add(new ChucNang("CN3", "NhanVien", ""));
        cacLoaiChucNang.add(new ChucNang("CN4", "Khac", ""));
        chucNangRepository.saveAll(cacLoaiChucNang);

        List<NhomNguoiDung> cacNhomNguoiDung = new ArrayList<>();
        cacNhomNguoiDung.add(new NhomNguoiDung("NND1", "NhomQuanLy"));
        cacNhomNguoiDung.add(new NhomNguoiDung("NND2", "NhomQuanTri"));
        cacNhomNguoiDung.add(new NhomNguoiDung("NND3", "NhomNhanVien"));
        cacNhomNguoiDung.add(new NhomNguoiDung("NND4", "NhomTuDo"));
        nhomNguoiDungRepository.saveAll(cacNhomNguoiDung);

        List<PhanQuyen> cacQuyen = new ArrayList<>();
        cacQuyen.add(new PhanQuyen(nhomNguoiDungRepository.findByMaNhom("NND1"), chucNangRepository.findByMaChucNang("CN2")));
        cacQuyen.add(new PhanQuyen(nhomNguoiDungRepository.findByMaNhom("NND2"), chucNangRepository.findByMaChucNang("CN1")));
        cacQuyen.add(new PhanQuyen(nhomNguoiDungRepository.findByMaNhom("NND3"), chucNangRepository.findByMaChucNang("CN3")));
        cacQuyen.add(new PhanQuyen(nhomNguoiDungRepository.findByMaNhom("NND4"), chucNangRepository.findByMaChucNang("CN4")));
        phanQuyenRepository.saveAll(cacQuyen);

        List<NguoiDung> cacNguoiDung = new ArrayList<>();
        cacNguoiDung.add(new NguoiDung("user1", passwordEncoder.encode("1"), nhomNguoiDungRepository.findByMaNhom("NND1")));
        cacNguoiDung.add(new NguoiDung("user2", passwordEncoder.encode("1"), nhomNguoiDungRepository.findByMaNhom("NND2")));
        cacNguoiDung.add(new NguoiDung("user3", passwordEncoder.encode("1"), nhomNguoiDungRepository.findByMaNhom("NND3")));
        cacNguoiDung.add(new NguoiDung("user4", passwordEncoder.encode("1"), nhomNguoiDungRepository.findByMaNhom("NND4")));
        nguoiDungRepository.saveAll(cacNguoiDung);
       

        NguoiDung nguoiDung = nguoiDungRepository.findByTenNguoiDung("user1");
        for (PhanQuyen phanQuyen : nguoiDung.getMaNhom().getPhanQuyenIds()) {
            System.out.println(phanQuyen.getMaChucNang().getTenChucNang());    
        }
    }
    
}
