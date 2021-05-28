package com.wms.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.wms.ValidationResponse;
import com.wms.dto.CaDTO;
import com.wms.dto.DichVuDTO;
import com.wms.dto.DoanhThuNgayDTO;
import com.wms.dto.HoaDonDTO;
import com.wms.dto.LoaiSanhDTO;
import com.wms.dto.MonAnDTO;
import com.wms.dto.SanhDTO;
import com.wms.dto.TiecDTO;
import com.wms.service.DaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class RestControllerApi {
    @Autowired
    DaoService daoService;

    @GetMapping(value = {"/ds-ca"})
    public List<CaDTO> layToanBoCa(){
        List<CaDTO> dsCa = new ArrayList<>();
        dsCa.add(new CaDTO("CA1", "Sang", "8"));
        dsCa.add(new CaDTO("CA2", "Chieu", "14"));
        return dsCa;
    } 

    @GetMapping(value = {"/ds-sanh"})
    public List<SanhDTO> layToanBoSanh(){
        List<SanhDTO> dsSanh = new ArrayList<>();
        dsSanh.add(new SanhDTO("S!", "Sang 1", "S", 10l, 1000000, ""));
        dsSanh.add(new SanhDTO("S2", "Sanh 2", "A", 10l, 1200000, ""));
        return dsSanh;
    } 

    @GetMapping(value = {"/ds-loai-sanh"})
    public List<LoaiSanhDTO> layToanBoLoaiSanh(){        
        return daoService.layDanhSachLoaiSanh();
    } 

    @GetMapping(value = {"/ds-mon-an"})
    public List<MonAnDTO> layToanBoMonAn(){
        List<MonAnDTO> dsMonAn = new ArrayList<>();
        dsMonAn.add(new MonAnDTO("MA1", "Thit", 1000000.0, ""));
        dsMonAn.add(new MonAnDTO("MA2", "Thit", 1000000.0, ""));
        return dsMonAn;
    } 

    @GetMapping(value = {"/ds-dich-vu"})
    public List<DichVuDTO> layToanBoDichVu(){
        List<DichVuDTO> dsDichVu = new ArrayList<>();
        dsDichVu.add(new DichVuDTO("DV1", "Thit", 10, 10.0, 0.0));
        dsDichVu.add(new DichVuDTO("DV2", "Thit", 100, 1.0, 0.5));
        return dsDichVu;
    }

    @GetMapping(value = {"/ds-tiec-cuoi"})
    public List<TiecDTO> layToanBoTiecCuoi(){
        List<TiecDTO> dsTiecCuoi = new ArrayList<>();
        dsTiecCuoi.add(new TiecDTO("T01", "Hoang", "han", "Sanh A", new Date(2017, 8, 12), 8, 100));
        dsTiecCuoi.add(new TiecDTO("T02", "Hoang", "Linh", "Sanh A", new Date(2017, 8, 12), 8, 100));
        return dsTiecCuoi;
    }

    @GetMapping(value = {"/ds-doanh-thu-ngay"})
    public List<DoanhThuNgayDTO> layToanBoDoanhThuThang(){
        List<DoanhThuNgayDTO> dsDoanhThuNgay = new ArrayList<>();
        dsDoanhThuNgay.add(new DoanhThuNgayDTO(1, 10, 10.56, 0.56));
        dsDoanhThuNgay.add(new DoanhThuNgayDTO(1, 10, 10.56, 0.56));
        return dsDoanhThuNgay;
    }

    @GetMapping(value = {"/thong-tin-hoa-don"})
    public HoaDonDTO lapHoaDonThanhToan(@RequestParam("maTiecCuoi") String maTiecCuoi) {
        HoaDonDTO chiTietHoaDon = null;
        List<DichVuDTO> dsDichVu = new ArrayList<>();
        List<MonAnDTO> dsMonAn = new ArrayList<>();
        dsMonAn.add(new MonAnDTO("MA1", "Thit", 1000000.0, ""));
        dsMonAn.add(new MonAnDTO("MA2", "Thit", 1000000.0, ""));

        dsDichVu.add(new DichVuDTO("DV1", "Thit", 10, 10.0, 0.0));
        dsDichVu.add(new DichVuDTO("DV2", "Thit", 100, 1.0, 0.5));

        chiTietHoaDon = new HoaDonDTO("Hoang", "Trang", new Date(2017, 8, 12), 10, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, 100.0, dsMonAn, dsDichVu);
        return chiTietHoaDon;
    }

    @PostMapping(value = {"/dat-tiec"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void datTiec(@RequestBody TiecDTO tiec) throws Exception{
        System.out.println("DAT TIEC");
        System.out.println(tiec.toString());
    }

    @PostMapping(value = {"/dat-sanh"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ValidationResponse>  datSanh(@RequestBody @Valid SanhDTO sanh, BindingResult result ) throws Exception{
        ValidationResponse res = new ValidationResponse();
        if(!result.hasErrors()){
            res.setStatus("SUCCESS");
            daoService.datSanhCuoi(sanh);  
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else{
            res.setStatus("FAIL");
            HashMap<String, String> errorFields = new HashMap<>();
            List container = new ArrayList<>();
    
            for (FieldError iterable_element : result.getFieldErrors()) {
                errorFields.put(iterable_element.getField(), iterable_element.getDefaultMessage());
            }
            container.add(errorFields);
            res.setErrorMessageList(container);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
       
              
    }
}
