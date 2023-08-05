package com.turkcell.socceronlinemanagement.model;

import com.turkcell.socceronlinemanagement.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     private String email;
    private String password;
   @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Team> teams;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //GrantedAuthority: security nin beklediği bir interface kullanıcının yetkilerini tututyor
        // getAuthorities: Kullanıcının yetkilerini döndüren bir method
        return List.of(new SimpleGrantedAuthority(role.name())); //SimpleGrantedAuthority: GrantedAuthority interface'ini implemente eden bir class
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { // Kullanıcı hesabı süresi geçmiş mi geçmemiş mi
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {   // Kullanıcı hesabı kilitli mi değil mi
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() { // Şifre geçerli mi değil mi
        return true;
    }
    @Override
    public boolean isEnabled() { // Kullanıcı aktif mi değil mi
        return true;
    }
}
