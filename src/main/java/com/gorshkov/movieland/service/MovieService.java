package com.gorshkov.movieland.service;

import com.gorshkov.movieland.model.Movie;
import com.gorshkov.movieland.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
