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
        // TODO Auto-generated method stub
        List<GrantedAuthority> roles = new ArrayList<>();
        for (PhanQuyen phanQuyen : phanQuyens) {
            roles.add(new SimpleGrantedAuthority(phanQuyen.getMaChucNang().getTenChucNang()));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return nguoiDung.getMatKhau();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return nguoiDung.getTenNguoiDung();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
