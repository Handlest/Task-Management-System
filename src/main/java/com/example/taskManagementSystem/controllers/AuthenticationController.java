package com.example.taskManagementSystem.controllers;
import com.example.taskManagementSystem.domain.dto.requests.UserRefreshTokenRequest;
import com.example.taskManagementSystem.domain.dto.requests.UserSignInRequest;
import com.example.taskManagementSystem.domain.dto.requests.UserSignUpRequest;
import com.example.taskManagementSystem.domain.dto.responses.UserJwtAuthenticationResponse;
import com.example.taskManagementSystem.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Авторизация и аутентификация")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<UserJwtAuthenticationResponse> register(@RequestBody @Validated UserSignUpRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserJwtAuthenticationResponse> authenticate(@RequestBody @Validated UserSignInRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<UserJwtAuthenticationResponse> refreshToken(@RequestBody @Validated UserRefreshTokenRequest request){
        return ResponseEntity.ok(service.refreshToken(request));
    }
}