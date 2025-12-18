package com.github.novabank.presentation.error;


import com.github.novabank.presentation.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
ApiError error = new ApiError("VALIDATION_ERROR", ex.getMessage());
return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}


@ExceptionHandler(RuntimeException.class)
public ResponseEntity<ApiError> handleRuntime(RuntimeException ex) {
ApiError error = new ApiError("SERVER_ERROR", ex.getMessage());
return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
}
}

