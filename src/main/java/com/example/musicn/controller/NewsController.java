package com.example.musicn.controller;

import com.example.musicn.entity.News;
import com.example.musicn.entity.User;
import com.example.musicn.repository.UserRepository;
import com.example.musicn.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private UserRepository userRepository;

    // Show all news
    @GetMapping
    public String showNewsList(Model model) {
        model.addAttribute("newsList", newsService.getAllNews());
        return "news";
    }

    // Show full news article (requires login)
    @GetMapping("/{id}")
    public String showNewsDetails(@PathVariable Long id, Model model) {
        Optional<News> news = newsService.getNewsById(id);
        if (news.isPresent()) {
            model.addAttribute("news", news.get());
            return "news-details";
        } else {
            return "redirect:/news";
        }
    }

    // Like a news article (Only for logged-in users)
    @PostMapping("/like/{id}")
    public String likeNews(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
            user.ifPresent(value -> newsService.increaseLikes(id));
        }
        return "redirect:/news/" + id;
    }
}
