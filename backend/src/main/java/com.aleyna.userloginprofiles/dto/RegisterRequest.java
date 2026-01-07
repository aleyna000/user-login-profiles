package com.aleyna.userloginprofiles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @Pattern(regexp = "\\d{11}", message = "TC 11 haneli olmalıdır")
    private String tcKimlikNo;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 8, message = "Şifre en az 8 karakter olmalıdır")
    private String password;

    @NotBlank(message = "Ad boş olamaz")
    private String firstName;

    @NotBlank(message = "Soyad boş olamaz")
    private String lastName;

    @Email(message = "Geçerli bir email giriniz")
    private String email;

    @NotBlank(message = "Telefon boş olamaz")
    private String phone;

    @NotBlank(message = "Doğum tarihi boş olamaz")
    private String birthDate; // String olarak alabiliriz, sonra parse edebiliriz

    // --- GETTER & SETTER ---
    public String getTcKimlikNo() { return tcKimlikNo; }
    public void setTcKimlikNo(String tcKimlikNo) { this.tcKimlikNo = tcKimlikNo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
}
