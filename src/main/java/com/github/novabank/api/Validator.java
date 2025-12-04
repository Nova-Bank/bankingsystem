package com.github.novabank.api;

import com.github.novabank.model.ValidationResult;

/**
 * Generic validator interface using the Strategy pattern.
 * @param <T> The type of object to validate
 */
public interface Validator<T> {
    /**
     * Validate the input.
     * @param input The object to validate
     * @return 
     * @return true if valid, false otherwise
     */
    ValidationResult validate(T input);
}