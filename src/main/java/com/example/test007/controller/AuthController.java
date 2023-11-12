package com.example.test007.controller;

import com.example.test007.entities.Roles;
import com.example.test007.entities.UserEntity;
import com.example.test007.model.LoginRequest;
import com.example.test007.model.LoginResponce;
import com.example.test007.model.RegisterDto;
import com.example.test007.repository.UserRepo;
import com.example.test007.security.JwtIssuer;
import com.example.test007.security.UserPrincipal;
import com.example.test007.security.UserPrincipalAuthenticationToken;
import com.example.test007.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @CrossOrigin(origins = "http://localhost:4200/auth/login", maxAge = 3600, allowCredentials = "true")
    @PostMapping("/auth/login")
    public LoginResponce login(@RequestBody @Validated LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty()) {
            System.out.println("Email and password are required");
        }
        return authService.attemtptLogin(request.getEmail(), request.getPassword());
    }


    @CrossOrigin(origins = "http://localhost:4200/auth/register", maxAge = 3600, allowCredentials="true")
    @PostMapping("/auth/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto) {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setNomEt(registerDto.getNomEt());
        user.setPrenomEt(registerDto.getPrenomEt());
        user.setCin(registerDto.getCin());
        user.setEcole(registerDto.getEcole());
        user.setDateNaissance(registerDto.getDateNaissance());

        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setRole(Roles.ROLE_USER);

        System.out.println(registerDto);
        System.out.println(user);
        userRepo.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
