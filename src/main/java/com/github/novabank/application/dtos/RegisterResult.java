package com.github.novabank.application.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * RegisterDTO for creating new accounts.
 */
public class RegisterResult {

    /** Full name of the user */
    @NotBlank(message = "Full name is required")
    private String fullName;

    /** Email for login */
    @NotBlank(message = "Email is required")
    private String email;

    /** Password for login */
    @NotBlank(message = "Password is required")
    private String password;

    /** Age of the user (used to calculate dateOfBirth) */
    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    private Integer age;

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
