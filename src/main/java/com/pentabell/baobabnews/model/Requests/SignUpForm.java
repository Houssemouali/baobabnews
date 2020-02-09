package com.pentabell.baobabnews.model.Requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;

public class SignUpForm {
//    @NotBlank
//    @Size(min = 3, max = 50)
//    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @Pattern(regexp = "(\\+|0)[0-9]{9}")
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date datenaiss;


    //@NotBlank
    private String nationality;

//    public String getName() {
//        return name;
//    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}