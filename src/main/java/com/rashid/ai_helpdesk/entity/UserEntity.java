package com.rashid.ai_helpdesk.entity;

import java.sql.Time;
import java.time.LocalTime;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity()
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Enumerated
    @Column(length = 255)
    private Erole name;

    @Column(length = 255)
    private String email;



    @Column(nullable= false, length = 255)

    private String passWord;

    @Column(updatable = false)
    private LocalTime created_at;

    @Column(updatable = false)
    private LocalTime updated_at;

    public Integer getUserId() {
        return userId;
    }

    public Erole getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(Erole name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setCreated_at(LocalTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalTime getCreated_at() {
        return created_at;
    }

    public LocalTime getUpdated_at() {
        return updated_at;
    }

}
