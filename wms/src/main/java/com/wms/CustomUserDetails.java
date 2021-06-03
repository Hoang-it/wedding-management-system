package com.wms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.wms.entities.NguoiDung;
import com.wms.entities.PhanQuyen;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class CustomUserDetails implements UserDetails{
    
    private NguoiDung nguoiDung;
    private Set<PhanQuyen> phanQuyens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        for (PhanQuyen phanQuyen : phanQuyens) {
            roles.add(new SimpleGrantedAuthority(phanQuyen.getMaChucNang().getTenChucNang()));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return nguoiDung.getMatKhau();
    }

    @Override
    public String getUsername() {
        return nguoiDung.getTenDangNhap();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
