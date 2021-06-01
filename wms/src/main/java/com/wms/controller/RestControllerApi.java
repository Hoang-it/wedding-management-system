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
import com.wms.dto.UserDTO;
import com.wms.entities.ThamSo;
import com.wms.service.DaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        return daoService.layToanBoDanhSachCa();
    } 

    @GetMapping(value = {"/ds-sanh"})
    public List<SanhDTO> layToanBoSanh(){
        return daoService.layToanBoDanhSachSanh();
    } 

    @GetMapping(value = {"/ds-loai-sanh"})
    public List<LoaiSanhDTO> layToanBoLoaiSanh(){        
        return daoService.layDanhSachLoaiSanh();
    } 

    @GetMapping(value = {"/ds-mon-an"})
    public List<MonAnDTO> layToanBoMonAn(){
        return daoService.layToanBoDanhSachMonAn();
    } 

    @GetMapping(value = {"/ds-dich-vu"})
    public List<DichVuDTO> layToanBoDichVu(){
        return daoService.layToanBoDanhSachDichVu();
    }

    @GetMapping(value = {"/ds-tiec-cuoi"})
    public List<TiecDTO> layToanBoTiecCuoi(){
        return daoService.layToanBoDanhSachTiecCuoi();
    }

    @GetMapping(value = {"/ds-tham-so"})
    public List<TiecDTO> layToanBoThamSo(){
        return daoService.layToanBoDanhSachTiecCuoi();
    }

    @GetMapping(value = {"/ds-tai-khoan"})
    public List<UserDTO> layToanBoTaiKhoan(){
        return daoService.layToanBoDanhSachNguoiDung();
    }

    @PostMapping(value = {"/cap-nhat-tai-khoan"})
    public ResponseEntity<ValidationResponse> capNhatTaiKhoan(@RequestBody UserDTO taiKhoan) {
        daoService.capNhatTaiKhoan(taiKhoan);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = {"/ds-doanh-thu-ngay"})
    public List<DoanhThuNgayDTO> layToanBoDoanhThuThang(){
        List<DoanhThuNgayDTO> dsDoanhThuNgay = new ArrayList<>();
        dsDoanhThuNgay.add(new DoanhThuNgayDTO(1, 10, 10.56, 0.56));
        dsDoanhThuNgay.add(new DoanhThuNgayDTO(1, 10, 10.56, 0.56));
        return dsDoanhThuNgay;
    }

    @GetMapping(value = {"/thong-tin-tai-khoan"})
    public UserDTO lapThongTinTaiKhoan(@RequestParam("tkid") Long maTaiKhoan) {
        return daoService.layThongTinTaiKhoan(maTaiKhoan);
    }

    @GetMapping(value = {"/quy-dinh-phat"})
    public ThamSo layQuyDinhPhat() {
        return daoService.layQuyDinhPhat();        
    }

    @PostMapping(value = {"/cap-nhat-quy-dinh"})
    public ResponseEntity<ValidationResponse> capNhatQuyDinh(@RequestBody ThamSo thamSo) {
        daoService.capNhatQuyDinh(thamSo);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = {"/thong-tin-dich-vu"})
    public DichVuDTO lapThongTinDichVu(@RequestParam("dvid") String maDichVu) {
        return daoService.layThongTinDichVu(maDichVu);
    }

    @PostMapping(value = {"/cap-nhat-dich-vu"})
    public ResponseEntity<ValidationResponse> capNhatThongTinDichVu(@RequestBody DichVuDTO dichVu) {
        daoService.capNhatThongTinDichVu(dichVu);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = {"/thong-tin-mon-an"})
    public MonAnDTO lapThongTinMonAn(@RequestParam("maid") String monAn) {
        return daoService.layThongTinMonAn(monAn);
    }

    @PostMapping(value = {"/cap-nhat-mon-an"})
    public ResponseEntity<ValidationResponse> capNhatThongTinMonAn(@RequestBody MonAnDTO monAn) {
        daoService.capNhatThongTinMonAn(monAn);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = {"/thong-tin-loai-sanh"})
    public LoaiSanhDTO lapThongTinLoaiSanh(@RequestParam("lsid") String loaiSanh) {
        return daoService.layThongTinLoaiSanh(loaiSanh);
    }

    @PostMapping(value = {"/cap-nhat-loai-sanh"})
    public ResponseEntity<ValidationResponse> capNhatThongTinLoaiSanh(@RequestBody LoaiSanhDTO sanh, @RequestParam("type") String type) {
        if ("update".equals(type)){
            daoService.capNhatThongTinLoaiSanh(sanh);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        if ("add".equals(type)){
            daoService.themLoaiSanh(sanh);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = {"/xoa-loai-sanh"})
    public ResponseEntity<ValidationResponse> xoaThongTinLoaiSanh(@RequestParam("lsid") String maLoaiSanh) {
        daoService.xoaThongTinLoaiSanh(maLoaiSanh);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = {"/thong-tin-ca"})
    public CaDTO lapThongTinCa(@RequestParam("caid") String maCa) {
        return daoService.layThongTinCa(maCa);
    }

    @PostMapping(value = {"/cap-nhat-ca"})
    public ResponseEntity<ValidationResponse> capNhatThongTinCa(@RequestBody CaDTO ca) {
        daoService.capNhatThongTinCa(ca);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = {"/thong-tin-hoa-don"})
    public HoaDonDTO lapHoaDonThanhToan(@RequestParam("maTiecCuoi") String maTiecCuoi) {
        return daoService.layThongTinHoaDon(maTiecCuoi);
    }

    @PostMapping(value = {"/dat-tiec"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ValidationResponse> datTiec(@RequestBody @Valid TiecDTO tiec, BindingResult result) throws Exception{
        System.out.println("DAT TIEC");
        
        ValidationResponse res = new ValidationResponse();
        if(!result.hasErrors()){
            res.setStatus("SUCCESS");
            daoService.datTiecCuoi(tiec);  
            System.out.println(tiec.toString());
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

    @PostMapping(value = {"/lap-hoa-don"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ValidationResponse>  lapHoaDon(@RequestBody @Valid HoaDonDTO hoaDon, BindingResult result ) throws Exception{
        ValidationResponse res = new ValidationResponse();
        if(!result.hasErrors()){
            res.setStatus("SUCCESS");
            daoService.lapHoaDon(hoaDon);
            //System.out.println(hoaDon.toString());
            //daoService.datSanhCuoi(sanh);  
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
