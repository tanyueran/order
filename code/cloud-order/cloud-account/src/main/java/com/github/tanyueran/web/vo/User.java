package com.github.tanyueran.web.vo;

import javax.validation.constraints.NotBlank;

public class User {
    @NotBlank(message = "账号不可为空")
    private String userCode;

    @NotBlank(message = "密码不可为空")
    private String password;

    private String authCode;// 验证码

    public User() {
    }

    public User(String userCode, String password, String authCode) {
        this.userCode = userCode;
        this.password = password;
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "userCode='" + userCode + '\'' +
                ", password='" + password + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
