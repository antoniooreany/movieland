package com.gorshkov.movieland.service;

import com.gorshkov.movieland.model.Review;
import com.gorshkov.movieland.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Iterable<Review> saveAll(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }
}
