package com.aleyna.userloginprofiles.dto;

import jakarta.validation.constraints.Pattern;

public class LoginRequest {

    @Pattern(regexp = "\\d{11}", message = "TC 11 haneli olmalıdır")
    private String tcKimlikNo;

    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
            message = "Şifre en az 8 karakter, harf ve sayı içermelidir"
    )
    private String password;

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
