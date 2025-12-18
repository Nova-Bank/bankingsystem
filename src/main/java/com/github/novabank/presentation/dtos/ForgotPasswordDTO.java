package com.github.novabank.presentation.dtos;

public class ForgotPasswordDTO {
    private String username;
    private String newPassword;

    public ForgotPasswordDTO() {}
    public ForgotPasswordDTO(String username, String newPassword) {
        this.username = username;
        this.newPassword = newPassword;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}