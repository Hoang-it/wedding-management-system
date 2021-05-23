package com.wms.controller;

import java.util.ArrayList;
import java.util.List;

import com.wms.dto.CaDTO;
import com.wms.dto.DichVuDTO;
import com.wms.dto.MonAnDTO;
import com.wms.dto.SanhDTO;
import com.wms.dto.TiecDTO;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class RestControllerApi {
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
        dsSanh.add(new SanhDTO("S!", "Sang 1", "S", 10, 1000000));
        dsSanh.add(new SanhDTO("S2", "Sanh 2", "A", 10, 1200000));
        return dsSanh;
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
        dsDichVu.add(new DichVuDTO("DV1", "Thit", 10, 10.0));
        dsDichVu.add(new DichVuDTO("DV2", "Thit", 100, 1.0));
        return dsDichVu;
    }

    @PostMapping(value = {"/dat-tiec"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void datTiec(@RequestBody TiecDTO tiec) throws Exception{
        System.out.println("DAT TIEC");
        System.out.println(tiec.toString());
    }
}
