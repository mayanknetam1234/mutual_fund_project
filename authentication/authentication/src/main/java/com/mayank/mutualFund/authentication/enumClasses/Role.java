package com.mayank.mutualFund.authentication.enumClasses;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mayank.mutualFund.authentication.enumClasses.Permission.*;


@AllArgsConstructor
public enum Role {
    USER(Set.of()),
    ADMIN(
         Set.of(
                 ADMIN_WRITE,ADMIN_READ,ADMIN_UPDATE
         )
    );
    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities=permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }




}
