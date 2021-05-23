package com.wms;

import java.util.Set;

import com.wms.entities.NguoiDung;
import com.wms.entities.PhanQuyen;
import com.wms.repositories.NguoiDungRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung nguoiDung = nguoiDungRepository.findByTenNguoiDung(username);
        Set<PhanQuyen> phanQuyen = nguoiDung.getMaNhom().getPhanQuyenIds();
        return new CustomUserDetails(nguoiDung, phanQuyen);
    }


}
