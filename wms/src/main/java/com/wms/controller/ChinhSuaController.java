package com.wms.controller;

import java.util.Collection;

import com.wms.CustomUserDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chinh-sua")
public class ChinhSuaController {
    CustomUserDetails customUserDetails = null;

    @ModelAttribute("roles")
    public Collection<? extends GrantedAuthority> getRoles(){
        customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getAuthorities();
    }

    @GetMapping(value = {"/loai-sanh"})
    public ModelAndView chinhSuaLoaiSanhPage(ModelAndView model){
        model.setViewName("/chinh-sua-loai-sanh");
        return model;
    }
    @GetMapping(value = {"/ca"})
    public ModelAndView chinhSuaCaPage(ModelAndView model){
        model.setViewName("/chinh-sua-ca");
        return model;
    }
    @GetMapping(value = {"/mon-an"})
    public ModelAndView chinhSuaMonAnPage(ModelAndView model){
        model.setViewName("/chinh-sua-mon-an");
        return model;
    }
    @GetMapping(value = {"/dich-vu"})
    public ModelAndView chinhSuaDichVuPage(ModelAndView model){
        model.setViewName("/chinh-sua-dich-vu");
        return model;
    }
}
