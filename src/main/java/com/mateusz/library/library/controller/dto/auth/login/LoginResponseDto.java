package com.mateusz.library.library.controller.dto.auth.login;

public class LoginResponseDto {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponseDto(String token) {
        this.token = token;
    }


}
