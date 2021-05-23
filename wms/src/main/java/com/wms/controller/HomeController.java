package com.wms.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wms.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController implements ErrorController{
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    CustomUserDetails customUserDetails = null;

    @ModelAttribute("roles")
    public Collection<? extends GrantedAuthority> getRoles(){
        customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getAuthorities();
    }

    @RequestMapping(value = { "/" })
    public ModelAndView homePage(ModelAndView model) {
        customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("");
        System.out.println("HOang");
        model.setViewName("index");
        model.addObject("name", customUserDetails.getUsername());
        model.addObject("roles", customUserDetails.getAuthorities());
        model.addObject("navActive", "#link-trang-chu");
        return model;
    }
    
    @RequestMapping(value = { "/logout" })
    public ModelAndView logoutPage(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {
        model.setViewName("login");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return model;
    }

    @RequestMapping(value = {"/access-denied"})
    public ModelAndView accessDeniedPage(ModelAndView model){
        model.setViewName("access-deny");
        return model;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        model.addAttribute("statusCode",statusCode);
        
        model.addAttribute("errorMessage","Error code : " + statusCode);
        return "error";
    }
    
    
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = { "/admin" })
    public ModelAndView adminPage(ModelAndView model) {
        model.setViewName("index-admin");
        return model;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = { "/user" })
    public ModelAndView userPage(ModelAndView model) {
        model.setViewName("index-user");
        return model;
    }

    @GetMapping(value = {"/tiep-nhan-sanh"})
    public ModelAndView tiepNhanSanh(ModelAndView model){
        model.setViewName("tiep-nhan-sanh");
        model.addObject("navActive", "#link-tiep-nhan-sanh");
        return model;
    }

    @GetMapping(value = {"/nhan-dat-tiec-cuoi"})
    public ModelAndView nhanDatTiecCuoi(ModelAndView model){
        model.setViewName("nhan-dat-tiec-cuoi");
        model.addObject("navActive", "#link-dat-tiec-cuoi");
        return model;
    }
    
    @GetMapping(value = {"/tra-cuu-tiec-cuoi"})
    public ModelAndView traCuuTiecCuoi(ModelAndView model){
        model.setViewName("tra-cuu-tiec-cuoi");
        model.addObject("navActive", "#link-tra-cuu-tiec-cuoi");
        return model;
    }

    @GetMapping(value = {"/lap-hoa-don-thanh-toan"})
    public ModelAndView lapHoaDonThanhToan(ModelAndView model){
        model.setViewName("lap-hoa-don-thanh-toan");
        model.addObject("navActive", "#link-lap-hoa-don");
        return model;
    }

    @GetMapping(value = {"/lap-bao-cao-thang"})
    public ModelAndView lapBaoCaoThang(ModelAndView model){
        model.setViewName("lap-bao-cao-thang");
        model.addObject("navActive", "#link-lap-bao-cao");
        return model;
    }

    @GetMapping(value = {"/thong-tin-tai-khoan"})
    public ModelAndView layThongTinTaiKhoan(ModelAndView model){
        model.setViewName("thong-tin-tai-khoan");
        return model;
    }

    @GetMapping(value = {"/chinh-sua"})
    public ModelAndView chinhSua(ModelAndView model){
        model.setViewName("chinh-sua");
        return model;
    }
}
