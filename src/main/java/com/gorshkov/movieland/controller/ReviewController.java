package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.Review;
import com.gorshkov.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gorshkov.movieland.util.Parser.parseReview;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/add")
    public String addReviews() {
        List<Review> reviews = parseReview();
        reviewService.saveAll(reviews);
        return "redirect:/users";
    }

    @GetMapping()
    public Iterable<Review> getAll(Model model) {
        Iterable<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("users", reviews);
        return reviews;
    }
}
