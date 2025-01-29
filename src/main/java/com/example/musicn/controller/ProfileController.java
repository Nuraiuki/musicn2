package com.example.musicn.controller;

import com.example.musicn.entity.News;
import com.example.musicn.entity.User;
import com.example.musicn.repository.NewsRepository;
import com.example.musicn.repository.UserRepository;
import com.example.musicn.service.FavouriteNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavouriteNewsService favouriteNewsService;

    @Autowired
    private NewsRepository newsRepository;

    // ✅ Show Profile Page
    @GetMapping
    public String showProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login"; // Redirect guests to login
        }

        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            model.addAttribute("user", user.get());

            // Get liked news
            List<News> likedNews = favouriteNewsService.getLikedNewsByUser(user.get());
            model.addAttribute("likedNews", likedNews);
        }
        return "profile";
    }

    // ✅ Update Profile Info (Email & Password)
    @PostMapping("/update")
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String email,
                                @RequestParam(required = false) String password) {
        Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setEmail(email);

            if (password != null && !password.isEmpty()) {
                user.setPassword(password); // Use hashing if required
            }

            userRepository.save(user);
        }

        return "redirect:/profile";
    }
}
