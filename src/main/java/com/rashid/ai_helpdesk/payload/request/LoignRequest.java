package com.rashid.ai_helpdesk.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoignRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String userEmail;

    @NotBlank
    private String passWord;

    public String getUsername(String userName) {
        return userName;
    }

    public String getEmail(String userEmail) {
        return userEmail;
    }

    public String getPassword(String userEmail) {
        return userEmail;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setPassword(String passWord) {
        this.passWord = passWord;
    }

}
