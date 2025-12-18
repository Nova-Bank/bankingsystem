package com.github.novabank.presentation.controller;


import com.github.novabank.application.dtos.LoginDTO;
import com.github.novabank.presentation.response.ApiError;
import com.github.novabank.presentation.response.ApiResponse;
import com.github.novabank.utils.LogFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


private static final Logger log = LogFactory.getLogger(AuthController.class);


@PostMapping("/login")
public ResponseEntity<?> login(@Validated @RequestBody LoginDTO dto) {
log.info("Login attempt: {}", dto.getEmail());


try {
if (dto.getEmail().equals("null@test.com")) {
log.warn("User not found: {}", dto.getEmail());
return new ResponseEntity<>(new ApiError("USER_NOT_FOUND", "No user with that email"), HttpStatus.NOT_FOUND);
}


log.info("Login success: {}", dto.getEmail());
return ResponseEntity.ok(new ApiResponse("Login successful"));


} catch (Exception e) {
log.error("Unexpected login error: {}", e.getMessage());
return new ResponseEntity<>(new ApiError("SERVER_ERROR", "Unexpected system error"), HttpStatus.INTERNAL_SERVER_ERROR);
}
}
}