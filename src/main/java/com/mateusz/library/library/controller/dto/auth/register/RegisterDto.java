package com.mateusz.library.library.controller.dto.auth.register;

import com.mateusz.library.library.commonTypes.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterDto {
    @NotBlank(message = "Password is required!")
    @Schema(name = "password", example = "okon")
    private String password;
    @NotBlank(message = "Username is required!")
    @Schema(name = "username", example = "mati123")
    private String username;
    @NotNull
    @Schema(name = "role", example = "ROLE_ADMIN")
    private UserRole role;
    @NotBlank(message = "Email is required!")
    @Schema(name = "email", example = "szwagier@wiaderko.pl")
    @Email
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterDto(String password, String username, UserRole role, String email) {
        this.password = password;
        this.username = username;
        this.role = role;
        this.email = email;
    }


}
