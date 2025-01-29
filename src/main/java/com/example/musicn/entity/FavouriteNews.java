package com.example.musicn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "favourite_news")
public class FavouriteNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    // ✅ Add Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) { // ✅ FIX: Ensure setter exists
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) { // ✅ FIX: Ensure setter exists
        this.news = news;
    }
}
