package com.pentabell.baobabnews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="users")
//@IdClass(User.class)
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name= "USER_TYPE",discriminatorType = DiscriminatorType.INTEGER)
@JsonIgnoreProperties
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Cascade(CascadeType.ALL)
//    ,generator="myseq"
//    @SequenceGenerator(name="myseq",sequenceName="MY_SEQ",allocationSize = 1, initialValue= 1)
    private long IdUser;


 /*   @NotBlank
    @Size(max = 50)
    @Email*/
    @Column(name = "email")
    @JsonIgnore
    private String email;
    /*
        @NotBlank
        @Size(min=3, max = 50)*/
    @Column(name = "username")
    private String username;

    @JsonIgnore
    //@NotBlank
    @Size(min=6, max = 100,message="password must be between 6 and 20 character long")
    @Column(name = "password")
    //@JsonIgnore
    private String password;

    /* @Pattern(regexp="(^$|[0-9]{11})")*/
    @Column(name="numtel")
    private String numtel;

    @Column(name = "nationality")
    private String nationality;

    //@JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy ="users_pp" )
    private Set<Problem>problemas;

    @OneToOne(mappedBy ="idUser")
    private SocialMedia socialMedia;

    //sionalisation
    @OneToMany(mappedBy = "sig_user")
    private Set<Signalisation>signalisations;

    //@JsonIgnore
    /*internaute reference to article bookmarks*/
    @OneToMany(mappedBy = "usersBookmark",
            cascade = javax.persistence.CascadeType.PERSIST,
            orphanRemoval = true)
    private Set<BookedArticle> articles = new HashSet<>();


    @OneToMany(mappedBy = "users_rating",
        cascade = javax.persistence.CascadeType.PERSIST,
            orphanRemoval = true)
    public Set<ArticleRating>articleRatings= new HashSet<>();
    //private ArticleRating articleRatings;

    public Users(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(min = 6, max = 100) String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Users(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String s, @NotBlank @Size(min = 6, max = 100) String password, long numtel, @NotBlank @Size(min = 3, max = 50) String username, Date dateNaissance) {
        this.username = username;
    }


    public Users(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 3, max = 50) String username, @Size(min = 6, max = 100, message = "password must be between 6 and 20 character long") String password, @Pattern(regexp = "(^$|[0-9]{11})") String numtel, String nationality, Date dateNaissance) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.numtel = numtel;
        this.nationality = nationality;
        this.dateNaissance = dateNaissance;
    }

    public Users(String email, String username, String password, String numtel, String nationality) {

    }

    public Users() {
    }

    public Users(String username) {

    }

    public long getIdUser() {
        return IdUser;
    }

    public void setIdUser(long idUser) {
        IdUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Set<Problem> getProblemas() {
        return problemas;
    }

    public void setProblemas(Set<Problem> problemas) {
        this.problemas = problemas;
    }
/* public Users(String email, String username, @Size(min = 6, max = 100, message = "password must be between 6 and 20 character long") String password, String numtel, Date dateNaissance) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.numtel = numtel;
        this.dateNaissance = dateNaissance;
    }*/

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "IdUser=" + IdUser +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
