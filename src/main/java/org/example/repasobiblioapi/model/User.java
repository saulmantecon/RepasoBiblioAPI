package org.example.hotelapi2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Column(name = "username")
    private String username;
    @Column(name = "pwd")
    private String pwd;
    @Transient
    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(String user, String pwd) {
        this.username = user;
        this.pwd = pwd;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
