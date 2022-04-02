package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.Review;
import com.gorshkov.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.gorshkov.movieland.util.FileReaderUtil.getRowsFromUrl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/reviews")
public class ReviewController {

    private static final String URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0f/download/review.txt";

    private final ReviewService reviewService;

    @GetMapping("/add")
    public String addReview() {

        List<String> rows = getRowsFromUrl(URL_STRING);
        List<Review> reviews = new ArrayList<>();
        Review review = new Review();

        for (int i = 0; i < rows.size(); i++) {
            if (i % 3 == 0) {
                review = new Review();
                review.setMovieName(rows.get(i));
            } else if (i % 3 == 1) {
                review.setUserName(rows.get(i));
            } else if (i % 3 == 2) {
                review.setReview(rows.get(i));
                reviews.add(review);
                log.info("user: {}", review);
                reviewService.addReview(review);
            }
        }
        return "redirect:/users";
    }

    @GetMapping()
    public Iterable<Review> getAll(Model model) {

        Iterable<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("users", reviews);
        return reviews;
    }
}
