package com.wms.controller;

import java.util.Collection;

import com.wms.CustomUserDetails;
import com.wms.entities.LoaiSanh;
import com.wms.service.DaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chinh-sua")
public class ChinhSuaController {

    CustomUserDetails customUserDetails = null;

    @Autowired 
    DaoService daoService;

    @ModelAttribute("roles")
    public Collection<? extends GrantedAuthority> getRoles(){
        customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getAuthorities();
    }

    @GetMapping(value = {"/loai-sanh"})
    public ModelAndView chinhSuaLoaiSanhPage(ModelAndView model){
        model.setViewName("/danh-sach-loai-sanh");
        return model;
    }

    @GetMapping(value = {"/ca"})
    public ModelAndView chinhSuaCaPage(ModelAndView model){
        model.setViewName("/danh-sach-ca");
        return model;
    }
    @GetMapping(value = {"/mon-an"})
    public ModelAndView chinhSuaMonAnPage(ModelAndView model){
        model.setViewName("/danh-sach-mon-an");
        return model;
    }
    @GetMapping(value = {"/dich-vu"})
    public ModelAndView chinhSuaDichVuPage(ModelAndView model){
        model.setViewName("/danh-sach-dich-vu");
        return model;
    }
    @GetMapping(value = {"/chinh-sua-quy-dinh-phat"})
    public ModelAndView chinhSuaQuyDinhPhat(ModelAndView model){
        model.setViewName("/ap-dung-quy-dinh-phat");
        return model;
    }
}
