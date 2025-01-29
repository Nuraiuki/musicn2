package com.example.musicn.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String audioUrl;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    // Getters and Setters
}
