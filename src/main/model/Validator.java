package main.model;

/**
 * Generic validator interface using the Strategy pattern.
 * @param <T> The type of object to validate
 */
public interface Validator<T> {
    /**
     * Validate the input.
     * @param input The object to validate
     * @return true if valid, false otherwise
     */
    boolean validate(T input);
}