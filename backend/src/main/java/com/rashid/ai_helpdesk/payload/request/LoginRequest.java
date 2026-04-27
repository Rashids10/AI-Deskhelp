package com.rashid.ai_helpdesk.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String usernamerEmail;

    @NotBlank
    private String password;

    public String getUsernameOrEmail() {
        return usernamerEmail;
    }

    public void setUserrEmail(String userEmail) {
        this.usernamerEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
