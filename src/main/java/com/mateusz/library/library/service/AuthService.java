package com.mateusz.library.library.service;

import com.mateusz.library.library.commonTypes.UserRole;
import com.mateusz.library.library.controller.dto.LoginDto;
import com.mateusz.library.library.controller.dto.LoginResponseDto;
import com.mateusz.library.library.controller.dto.RegisterDto;
import com.mateusz.library.library.controller.dto.RegisterResponseDto;
import com.mateusz.library.library.infrastructure.entity.AuthEntity;
import com.mateusz.library.library.infrastructure.entity.UserEntity;
import com.mateusz.library.library.infrastructure.repository.AuthRepository;
import com.mateusz.library.library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDto register(RegisterDto dto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());

        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword((passwordEncoder.encode(dto.getPassword())));
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole((dto.getRole()));
        authEntity.setUser(userEntity);

        authRepository.save(authEntity);

        return new RegisterResponseDto(userEntity.getId(), authEntity.getUsername(), authEntity.getRole());
    }

    public LoginResponseDto login(LoginDto dto){
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException::new);

        if(!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())){
             throw new RuntimeException();
        }
        String token = jwtService.generateToken(authEntity);

        return new LoginResponseDto(token);
    }

}
