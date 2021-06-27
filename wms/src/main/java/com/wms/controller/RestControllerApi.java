package com.wms.controller;

import java.math.BigDecimal;
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
import com.wms.dto.NhomNguoiDungDTO;
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

    @GetMapping(value = {"/tong-doanh-thu"})
    public BigDecimal layDoanhThuThang(@RequestParam("thang") String thang, @RequestParam("nam") String nam){        
        return daoService.layDoanhThuThang(thang, nam);
    }

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

    @GetMapping(value = {"/sanh"})
    public List<SanhDTO> laySanh(@RequestParam("maSanh") String maSanh){        
        return daoService.laySanh(maSanh);
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

    @GetMapping(value = {"/ds-hoa-don"})
    public List<HoaDonDTO> layToanBoHoaDon(){
        return daoService.layToanBoDanhSachHoaDon();
    }

    @GetMapping(value = {"/tiec-cuoi"})
    public List<TiecDTO> layTiecCuoi(@RequestParam("maTiecCuoi") String maTiecCuoi){
        return daoService.layTiecCuoi(maTiecCuoi);
    }

    @DeleteMapping(value = {"/tiec-cuoi/xoa"})
    public void xoaTiecCuoi(@RequestParam("maTiecCuoi") String maTiecCuoi){
        daoService.xoaTiecCuoi(maTiecCuoi);
    }

    @DeleteMapping(value = {"/hoa-don/xoa"})
    public void xoaHoaDon(@RequestParam("maTiecCuoi") String maTiecCuoi){
        daoService.xoaHoaDon(maTiecCuoi);
    }

    @GetMapping(value = {"/tiec-cuoi/ds-mon-an"})
    public List<MonAnDTO> layMonAnTrongBuoiTiec(@RequestParam("maTiecCuoi") String maTiecCuoi){
        return daoService.layDanhSachMonAnTrongBuoiTiec(maTiecCuoi);
    } 

    @GetMapping(value = {"/tiec-cuoi/ds-dich-vu"})
    public List<DichVuDTO> layDichVuTrongBuoiTiec(@RequestParam("maTiecCuoi") String maTiecCuoi){
        return daoService.layDanhSachDichVuTrongBuoiTiec(maTiecCuoi);
    }

    @GetMapping(value = {"/ds-tham-so"})
    public List<TiecDTO> layToanBoThamSo(){
        return daoService.layToanBoDanhSachTiecCuoi();
    }

    @GetMapping(value = {"/ds-tai-khoan"})
    public List<UserDTO> layToanBoTaiKhoan(){
        return daoService.layToanBoDanhSachNguoiDung();
    }

    @GetMapping(value = {"/ds-nhom-tai-khoan"})
    public List<NhomNguoiDungDTO> layToanNhomTaiKhoan(){
        return daoService.layToanBoDanhSachNhomNguoiDung();
    }

    @GetMapping(value = {"/ds-nguoi-dung"})
    public List<UserDTO> layNguoiDungTrongNhom(@RequestParam("maNhom") String maNhom){
        return daoService.layDanhSachNguoiDungTrongNhom(maNhom);
    }

    @PostMapping(value = {"/cap-nhat-tai-khoan"})
    public ResponseEntity<ValidationResponse> capNhatTaiKhoan(@RequestBody UserDTO taiKhoan) {
        daoService.capNhatTaiKhoan(taiKhoan);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping(value = {"/cap-nhat-sanh"})
    public ResponseEntity<ValidationResponse> capNhatSanh(@RequestBody SanhDTO sanh) {
        ValidationResponse res = new ValidationResponse();
        daoService.capNhatSanh(sanh);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping(value = {"/cap-nhat-nhom-tai-khoan"})
    public ResponseEntity<ValidationResponse> capNhatNhomTaiKhoan(@RequestBody NhomNguoiDungDTO nhom) {
        daoService.capNhatNhomTaiKhoan(nhom);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @GetMapping(value = {"/ds-doanh-thu-ngay"})
    public List<DoanhThuNgayDTO> layToanBoDoanhThuThang(@RequestParam("thang") String thang, @RequestParam("nam") String nam){        
        return daoService.layDanhSachDoanhThuNgay(thang, nam);
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

    @PostMapping(value = {"/them-dich-vu"})
    public ResponseEntity<ValidationResponse> themThongTinDichVu(@RequestBody DichVuDTO dichVu) {
        daoService.themThongDichVu(dichVu);
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

    @PostMapping(value = {"/them-mon-an"})
    public ResponseEntity<ValidationResponse> themThongTinMonAn(@RequestBody MonAnDTO monAn) {
        daoService.themThongMonAn(monAn);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = {"/thong-tin-loai-sanh"})
    public LoaiSanhDTO lapThongTinLoaiSanh(@RequestParam("lsid") String loaiSanh) {
        return daoService.layThongTinLoaiSanh(loaiSanh);
    }

    @PostMapping(value = {"/cap-nhat-loai-sanh"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ValidationResponse> capNhatThongTinLoaiSanh(@RequestBody @Valid LoaiSanhDTO sanh, 
            @RequestParam("type") String type, BindingResult result) throws Exception{
        ValidationResponse res = new ValidationResponse();
        if (!result.hasErrors()){
            res.setStatus("SUCCESS");
            if ("bql".equals(HomeController.role)){
                if ("update".equals(type)){
                    daoService.capNhatThongTinLoaiSanh(sanh);
                    return new ResponseEntity<>(res, HttpStatus.OK);
                }
                if ("add".equals(type)){
                    daoService.themLoaiSanh(sanh);
                    return new ResponseEntity<>(res, HttpStatus.OK);
                }
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
        } else{
            res.setStatus("FAIL");
            System.out.println("FAILLLLLLLLLL");
            HashMap<String, String> errorFields = new HashMap<>();
            List container = new ArrayList<>();
    
            for (FieldError iterable_element : result.getFieldErrors()) {
                System.out.println(iterable_element.getDefaultMessage());
                errorFields.put(iterable_element.getField(), iterable_element.getDefaultMessage());
            }
            container.add(errorFields);
            res.setErrorMessageList(container);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        
        
    }



    @DeleteMapping(value = {"/xoa-sanh"})
    public ResponseEntity<ValidationResponse> xoaThongTinSanh(@RequestParam("sid") String maSanh) {
        daoService.xoaThongTinSanh(maSanh);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(value = {"/xoa-ca"})
    public ResponseEntity<ValidationResponse> xoaThongTinCa(@RequestParam("maCa") String maCa) {
        daoService.xoaThongTinCa(maCa);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(value = {"/xoa-mon-an"})
    public ResponseEntity<ValidationResponse> xoaThongTinMonAn(@RequestParam("maMonAn") String maMonAn) {
        daoService.xoaThongTinMonAn(maMonAn);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(value = {"/xoa-dich-vu"})
    public ResponseEntity<ValidationResponse> xoaThongTinDichVu(@RequestParam("maDichVu") String maDichVu) {
        daoService.xoaThongTinDichVu(maDichVu);
        return new ResponseEntity<>(null, HttpStatus.OK);
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

    @PostMapping(value = {"/them-ca"})
    public ResponseEntity<ValidationResponse> themThongTinCa(@RequestBody CaDTO ca) {
        daoService.themThongTinCa(ca);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = {"/thong-tin-hoa-don"})
    public HoaDonDTO lapHoaDonThanhToan(@RequestParam("maTiecCuoi") String maTiecCuoi) {
        return daoService.layThongTinHoaDon(maTiecCuoi);
    }

    @GetMapping(value = {"/thong-tin-tiec-cuoi"})
    public HoaDonDTO lapThongTinTiecCuoi(@RequestParam("maTiecCuoi") String maTiecCuoi) {
        return daoService.layThongTinTiecCuoi(maTiecCuoi);
    }

    @PostMapping(value = {"/dat-tiec"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ValidationResponse> datTiec(@RequestBody @Valid TiecDTO tiec, BindingResult result) throws Exception{
        System.out.println("DAT TIEC");
        HashMap<String, String> errorFields = new HashMap<>();
        List container = new ArrayList<>();

        ValidationResponse res = new ValidationResponse();
        if(!result.hasErrors()){
            res.setStatus("SUCCESS");
            if (!daoService.datTiecCuoi(tiec, res)){
                errorFields.put("ca", "Không thể đặt vào ngày này");
                container.add(errorFields);
                res.setErrorMessageList(container);
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }  
            System.out.println(tiec.toString());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else{
            res.setStatus("FAIL");
            
    
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
