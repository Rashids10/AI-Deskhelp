package com.rashid.ai_helpdesk.payload.response;

import java.util.List;

public class JwtResponse {


    private String token;
    private String type = "Bearer";
    private Long id;
    private String userName;
    private String userEmail;
    private List<String> roles;



    public JwtResponse(String accesToken, Long id, String userName, String userEmail, List<String> roles){

        this.id= id;
        this.userName= userName;
        this.userEmail= userEmail;
        this.roles= roles;

    }



     public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUsername() {
    return userName;
  }

  public void setUsername(String useNname) {
    this.userName = userName;
  }

  public List<String> getRoles() {
    return roles;
  }
}


