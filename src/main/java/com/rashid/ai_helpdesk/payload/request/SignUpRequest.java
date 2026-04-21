package com.rashid.ai_helpdesk.payload.request;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpRequest {






     @NotBlank
     @Size(min=4, max=20)
    private String userName;

    @NotBlank
    @Size(max=50)
    @Email
    private String userEmail;

    @NotBlank
    @Size(min=10, max=60)

    private String passWord;


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
