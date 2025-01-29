package com.example.musicn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private int likesCount = 0; // Default value

    // ✅ Add Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikesCount() { // ✅ FIX: Ensure getter exists
        return likesCount;
    }

    public void setLikesCount(int likesCount) { // ✅ FIX: Ensure setter exists
        this.likesCount = likesCount;
    }
}
