package com.pentabell.baobabnews.model.Requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class SignUpJournalistForm {

    @NotBlank
    @Size(min = 2, max = 30)
    private String nom;

    @NotBlank
    @Size(min = 2, max = 30)
    private String prenom;

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

    @NotBlank
    private String nationality;

    //@NotBlank
    private String actualEntreprise;

    @NotNull
    private int experience;


    private String numtel;

    //@NotBlank
    private String motivationtext;

    private String status="en cours";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date datenaiss;

    @Lob
    //@Column(name = "portefolio", columnDefinition="BLOB")
    private byte[] portefolio;
    //CV
    @Lob
    //@Column(name = "cv", columnDefinition="BLOB")
    private byte[] cv;


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

    public String getActualEntreprise() {
        return actualEntreprise;
    }

    public void setActualEntreprise(String actualEntreprise) {
        this.actualEntreprise = actualEntreprise;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getMotivationtext() {
        return motivationtext;
    }

    public void setMotivationtext(String motivationtext) {
        this.motivationtext = motivationtext;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public byte[] getPortefolio() {
        return portefolio;
    }

    public void setPortefolio(byte[] portefolio) {
        this.portefolio = portefolio;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}