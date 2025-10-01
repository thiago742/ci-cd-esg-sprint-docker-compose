package com.esg.esg_monitor.dto;

import com.esg.esg_monitor.enums.Role;
//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter

public class RegisterRequest {
    private String username;
    private String password;
    private Role role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
