package com.example.musicn.repository;

import com.example.musicn.entity.FavouriteNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteNewsRepository extends JpaRepository<FavouriteNews, Long> {
}
