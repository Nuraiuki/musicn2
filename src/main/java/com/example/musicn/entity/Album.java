package com.example.musicn.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Music> songs;

    // Getters and Setters
}
