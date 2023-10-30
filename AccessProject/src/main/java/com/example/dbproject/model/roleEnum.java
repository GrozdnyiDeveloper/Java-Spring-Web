package com.example.dbproject.model;

import org.springframework.security.core.GrantedAuthority;

public enum roleEnum implements GrantedAuthority {
    USER, ADMIN, MANAGER;
    @Override
    public String getAuthority()
    {
        return name();
    }
}
