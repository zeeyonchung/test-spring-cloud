package com.example.app.domain;

import lombok.Data;

@Data
public class Member {

    Long id;
    String name;
    String username;

    public Member() {}

    public Member(String name, String username) {
        this.name = name;
        this.username = username;
    }
}
