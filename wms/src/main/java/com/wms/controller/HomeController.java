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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController implements ErrorController{
    public static String role = "";
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    CustomUserDetails customUserDetails = null;

    @ModelAttribute("role")
    public String getUserRole(){
        return role;
    }

    @RequestMapping("dangXuat")
    public ModelAndView logOut(ModelAndView model, HttpServletRequest request, HttpServletResponse response){
        model.setViewName("login");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        role = "";
        model.addObject("role", role);
        return model;
    }

    @RequestMapping(value = { "/" })
    public ModelAndView homePage(ModelAndView model) {
        //customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       
        System.out.println(role);
        model.setViewName("index");
        // model.addObject("name", customUserDetails.getUsername());
        model.addObject("role", role);
        model.addObject("navActive", "#link-trang-chu");
        return model;
    }

    @GetMapping(value = {"/chinh-sua-quy-dinh-phat"})
    public ModelAndView chinhSuaQuyDinhPhat(ModelAndView model){
        if (!"bql".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("/ap-dung-quy-dinh-phat");
        return model;
    }

    @GetMapping(value = {"/ds/sanh"})
    public ModelAndView chinhSuaSanhPage(ModelAndView model){
        model.setViewName("/danh-sach-sanh");
        return model;
    }

    @GetMapping(value = {"/ds/ca"})
    public ModelAndView chinhSuaCaPage(ModelAndView model){
        if (!"bql".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("/danh-sach-ca");
        return model;
    }

    @GetMapping(value = {"/ds/loai-sanh"})    
    public ModelAndView chinhSuaLoaiSanhPage(ModelAndView model){
        if ("admin".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("/danh-sach-loai-sanh");
        return model;
    }

    @GetMapping(value = {"/ds/mon-an"})
    public ModelAndView chinhSuaMonAnPage(ModelAndView model){
        if ("admin".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("/danh-sach-mon-an");
        return model;
    }

    @GetMapping(value = {"/ds/dich-vu"})
    public ModelAndView chinhSuaDichVuPage(ModelAndView model){
        if ("admin".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("/danh-sach-dich-vu");
        return model;
    }
    
    @RequestMapping(value = { "/quan-tri-vien" })
    public ModelAndView adminHomePage(ModelAndView model) {
        role = "admin";
        return homePage(model);
    }

    @RequestMapping(value = { "/nhan-vien" })
    public ModelAndView nhanVienHomePage(ModelAndView model) {
        role = "nhanvien";
        return homePage(model);
    }

    @RequestMapping(value = { "/bql" })
    public ModelAndView bqlHomePage(ModelAndView model) {
        role = "bql";
        return homePage(model);
    }

    @RequestMapping(value = { "/phan-quyen" })
    public ModelAndView phanQuyenPage(ModelAndView model) {
        model.setViewName("index-admin");
        return model;
    }

    // @RequestMapping(value = { "/logout" })
    // public ModelAndView logoutPage(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {
    //     model.setViewName("login");
    //     role = "";
    //     System.out.println("Quyen : " + role);
    //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //     if (auth != null) {
    //         new SecurityContextLogoutHandler().logout(request, response, auth);
    //     }
    //     return model;
    // }

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
        if (!"bql".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("tiep-nhan-sanh");
        model.addObject("navActive", "#link-tiep-nhan-sanh");
        return model;
    }

    @GetMapping(value = {"/nhan-dat-tiec-cuoi"})
    public ModelAndView nhanDatTiecCuoi(ModelAndView model){
        if (!"nhanvien".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("nhan-dat-tiec-cuoi");
        model.addObject("navActive", "#link-dat-tiec-cuoi");
        return model;
    }
    
    @GetMapping(value = {"/tra-cuu-tiec-cuoi"})
    public ModelAndView traCuuTiecCuoi(ModelAndView model){
        if ("admin".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("tra-cuu-tiec-cuoi");
        model.addObject("navActive", "#link-tra-cuu-tiec-cuoi");
        return model;
    }

    @GetMapping(value = {"/lap-hoa-don-thanh-toan"})
    public ModelAndView lapHoaDonThanhToan(ModelAndView model, @RequestParam("maTiecCuoi") String maTiecCuoi){
        if (!"nhanvien".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("lap-hoa-don-thanh-toan");

        model.addObject("navActive", "#link-lap-hoa-don");
        model.addObject("maTiecCuoi", maTiecCuoi);
        return model;
    }


    @GetMapping(value = {"/lap-bao-cao-thang"})
    public ModelAndView lapBaoCaoThang(ModelAndView model){
        if (!"bql".equals(role)){
            model.setViewName("/access-deny");
            return model;
        }
        model.setViewName("lap-bao-cao-thang");
        model.addObject("navActive", "#link-lap-bao-cao");
        return model;
    }

    @GetMapping(value = {"/chi-tiet-bao-cao-thang"})
    public ModelAndView layChiTietBaoCaoThang(ModelAndView model, @RequestParam("thang") String nam, @RequestParam("thang") String thang){
        model.setViewName("/chi-tiet-bao-cao-thang");
        model.addObject("navActive", "#link-lap-bao-cao");
        model.addObject("thang", thang);
        model.addObject("nam", nam);
        model.addObject("tongDoanhThu", 10.78);
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
