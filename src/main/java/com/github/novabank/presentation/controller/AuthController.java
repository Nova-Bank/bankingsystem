package com.github.novabank.presentation.controller;

import com.github.novabank.presentation.dtos.ForgotPasswordDTO;
import com.github.novabank.presentation.dtos.LoginRequestDTO;
import com.github.novabank.presentation.dtos.RegisterRequest;
import com.github.novabank.presentation.response.ApiError;
import com.github.novabank.presentation.response.ApiResponse;
import com.github.novabank.utils.LogFactory;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LogFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO dto) {
        log.info("Login attempt: {}", dto.getUsername());

        try {
            if (dto.getUsername().equals("null@test.com")) {
                log.warn("User not found: {}", dto.getUsername());
                return new ResponseEntity<>(new ApiError("USER_NOT_FOUND",
                        "No user with that email"), HttpStatus.NOT_FOUND);
            }

            log.info("Login success: {}", dto.getUsername());
            return ResponseEntity.ok(new ApiResponse("Login successful"));
        } catch (Exception e) {
            log.error("Unexpected login error: {}", e.getMessage());
            return new ResponseEntity<>(new ApiError("SERVER_ERROR",
                    "Unexpected system error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest dto) {
        log.info("Register request: {}", dto.getEmail());
        return ResponseEntity.ok(new ApiResponse("Registration successful"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody ForgotPasswordDTO dto) {
        log.info("Password reset request for username={}", dto.getUsername());
        return ResponseEntity.ok(new ApiResponse("Password reset successful"));
    }
}
