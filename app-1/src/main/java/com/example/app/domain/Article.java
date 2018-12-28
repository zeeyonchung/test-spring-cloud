package com.example.app.domain;

import lombok.Data;

@Data
public class Article {
    int idx;

    String title;
    String content;
    String author;

    public Article(int idx, String title, String content, String author) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
