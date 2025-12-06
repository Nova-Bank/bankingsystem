package com.github.novabank.domain.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents the result of a validation operation.
 * Contains success/failure status and any error messages.
 */
public class ValidationResult {
    
    private final boolean valid;
    private final List<String> errors;

    private ValidationResult(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = errors;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, Collections.emptyList());
    }

    public static ValidationResult failure(String... errors) {
        return new ValidationResult(false, Arrays.asList(errors));
    }

    public static ValidationResult failure(List<String> errors) {
        return new ValidationResult(false, new ArrayList<>(errors));
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    /**
     * Get all errors as a single formatted string.
     */
    public String getErrorMessage() {
        return String.join(", ", errors);
    }

    @Override
    public String toString() {
        if (valid) {
            return "ValidationResult: SUCCESS";
        } else {
            return "ValidationResult: FAILURE - " + getErrorMessage();
        }
    }
}