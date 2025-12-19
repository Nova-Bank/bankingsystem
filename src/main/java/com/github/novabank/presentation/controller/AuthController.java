package com.github.novabank.presentation.controller;

import com.github.novabank.presentation.dtos.LoginRequest;
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
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest dto) {
        log.info("Login attempt for email={}", dto.getEmail());

        try {
            // Example placeholder: replace with your actual login service
            if ("null@test.com".equals(dto.getEmail())) {
                log.warn("User not found: {}", dto.getEmail());
                return new ResponseEntity<>(
                        new ApiError("USER_NOT_FOUND", "No user with that email"),
                        HttpStatus.NOT_FOUND
                );
            }

            log.info("Login successful: {}", dto.getEmail());
            return ResponseEntity.ok(new ApiResponse("Login successful"));
        } catch (Exception e) {
            log.error("Unexpected login error: {}", e.getMessage(), e);
            return new ResponseEntity<>(
                    new ApiError("SERVER_ERROR", "Unexpected system error"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest dto) {
        log.info("Register request for email={}", dto.getEmail());

        // TODO: call RegisterCustomer service to actually create account
        return ResponseEntity.ok(new ApiResponse("Registration successful"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody ForgotPasswordResult dto) {
        log.info("Password reset request for email={}", dto.getUsername());

        // TODO: call ChangePassword service to actually reset password
        return ResponseEntity.ok(new ApiResponse("Password reset successful"));
    }
}
