package com.rashid.ai_helpdesk.service;

import java.util.Collection;

import org.hibernate.boot.internal.Extends;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class UserDeteils implements UserDetails {
    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDeteils(Long id,
            String username,
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities) {

        this.id = id;

        this.username = username;

        this.email = email;

        this.password = password;
        this.authorities = authorities;
    }

    /*
     * gibt User-Rollen zurück
     * wird von Spring für Security benutzt
     * entscheidet, was der User darf
     * 
     * 
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override

    // check if account is expired

    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // check if account is locked

    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // check if password is expired

    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // check if user is active

    public boolean isEnabled() {
        return true;
    }

}
