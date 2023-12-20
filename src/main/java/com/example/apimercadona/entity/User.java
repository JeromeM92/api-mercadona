package com.example.apimercadona.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/*
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    //Equivaut à autoIncremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;

    private String email;
    private String password;
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User() {

    }
}*/
