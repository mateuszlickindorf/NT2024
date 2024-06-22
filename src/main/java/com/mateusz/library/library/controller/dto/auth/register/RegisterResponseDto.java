package com.mateusz.library.library.controller.dto.auth.register;

import com.mateusz.library.library.commonTypes.UserRole;

public class RegisterResponseDto {
    private String username;
    private UserRole role;
    private Long userId;

    public RegisterResponseDto(Long userId, String username, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.role = role;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
