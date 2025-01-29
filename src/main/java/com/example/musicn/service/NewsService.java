package com.example.musicn.service;

import com.example.musicn.entity.News;
import com.example.musicn.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }

    public void increaseLikes(Long newsId) {
        Optional<News> newsOpt = newsRepository.findById(newsId);
        if (newsOpt.isPresent()) {
            News news = newsOpt.get();
            news.setLikesCount(news.getLikesCount() + 1); // ✅ FIX: Now `getLikesCount()` exists
            newsRepository.save(news);
        }
    }
}
