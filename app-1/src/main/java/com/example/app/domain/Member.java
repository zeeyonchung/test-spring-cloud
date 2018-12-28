package com.example.app.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name="user")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idx")
    int id;

    @Column(name="name")
    String name;

    @Column(name="email")
    String email;

    public Member() {}

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
