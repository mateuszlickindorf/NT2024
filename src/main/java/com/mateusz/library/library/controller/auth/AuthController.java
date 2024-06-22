package com.mateusz.library.library.controller.auth;

import com.mateusz.library.library.controller.dto.auth.login.LoginDto;
import com.mateusz.library.library.controller.dto.auth.login.LoginResponseDto;
import com.mateusz.library.library.controller.dto.auth.register.RegisterDto;
import com.mateusz.library.library.controller.dto.auth.register.RegisterResponseDto;
import com.mateusz.library.library.service.auth.AuthService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Auth")
public class AuthController {
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    private final AuthService authService;

    @PostMapping("/register")
    @ApiResponse(responseCode = "201", description = "Register succesful")
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterDto requestBody) {
        RegisterResponseDto dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    @SecurityRequirements
    @ApiResponse(responseCode = "200", description = "Login succesful")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto requestBody) {
        LoginResponseDto dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
