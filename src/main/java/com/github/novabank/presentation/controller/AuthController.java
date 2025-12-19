package com.github.novabank.presentation.controller;

import com.github.novabank.application.dtos.ForgotPasswordResult;
import com.github.novabank.application.dtos.LoginResult;
import com.github.novabank.application.services.customer_accounts.LoginApplicationService;
import com.github.novabank.application.services.customer_accounts.RegisterApplicationService;
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
@RequestMapping("/api")
public class AuthController {

    private static final Logger log = LogFactory.getLogger(AuthController.class);

    private final RegisterApplicationService registerApplicationService;
    private final LoginApplicationService loginApplicationService;

    public AuthController(RegisterApplicationService registerApplicationService, LoginApplicationService loginApplicationService) {
        this.registerApplicationService = registerApplicationService;
        this.loginApplicationService = loginApplicationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest dto) {
        log.info("Login attempt for email={}", dto.getEmail());

        try {
            LoginResult result = loginApplicationService.execute(dto);

            if (result == null) {
                log.warn("Login failed for user: {}", dto.getEmail());
                return new ResponseEntity<>(
                        new ApiError("INVALID_CREDENTIALS", "Invalid email or password"),
                        HttpStatus.UNAUTHORIZED
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
        try {
            registerApplicationService.execute(dto);
            return ResponseEntity.ok(new ApiResponse("Registration successful"));
        } catch (Exception e) {
            log.error("Unexpected registration error: {}", e.getMessage(), e);
            return new ResponseEntity(
                    new ApiError("SERVER_ERROR", "Unexpected system error during registration"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody ForgotPasswordResult dto) {
        log.info("Password reset request for email={}", dto.getUsername());

        // TODO: call ChangePassword service to actually reset password
        return ResponseEntity.ok(new ApiResponse("Password reset successful"));
    }
}
