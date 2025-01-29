package com.example.musicn.service;

import com.example.musicn.entity.FavouriteNews;
import com.example.musicn.entity.News;
import com.example.musicn.entity.User;
import com.example.musicn.repository.FavouriteNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouriteNewsService {
    @Autowired
    private FavouriteNewsRepository favouriteNewsRepository;

    public List<News> getLikedNewsByUser(User user) {
        List<FavouriteNews> favNews = favouriteNewsRepository.findAll();
        return favNews.stream()
                .filter(fav -> fav.getUser().getId().equals(user.getId()))
                .map(FavouriteNews::getNews)
                .collect(Collectors.toList());
    }
}
