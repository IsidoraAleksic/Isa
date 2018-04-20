package com.example.demo.dto;

import com.example.demo.model.User;

public class UserDTO {
    private Long userId;

    private String passwordOld;

    private String passwordNew;

    public UserDTO(){

    }

    public UserDTO(Long userId, String passwordOld, String passwordNew) {
        this.userId = userId;
        this.passwordOld = passwordOld;
        this.passwordNew = passwordNew;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", passwordOld='" + passwordOld + '\'' +
                ", passwordNew='" + passwordNew + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }
}
