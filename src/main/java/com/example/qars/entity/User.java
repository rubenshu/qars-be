package com.example.qars.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {

    /** Entity fields **/
    @Column(name = "username")
    private String username;
    @Size(min = 6, message = "Wachtwoord moet langer zijn dan 6 tekens")
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    /** Constructor for creation **/
    public User(){}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Add role attribute to spring security authorities
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.role));
        return authorities;
    }

    /** Getters & Setters **/
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Spring Security UserDetails interface getter & setter
    // Todo: maybe implement these spring security attributes
    public boolean isAccountNonExpired() { return true; }
    public boolean isAccountNonLocked() { return true; }
    public boolean isCredentialsNonExpired() { return true; }
    public boolean isEnabled() { return true; }

}
