package com.gorshkov.movieland.service;

import com.gorshkov.movieland.model.Movie;
import com.gorshkov.movieland.model.dto.MovieWithLink;
import com.gorshkov.movieland.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gorshkov.movieland.util.dtoConvector.MovieDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie add(Movie movie) {
        return movieRepository.save(movie);
    }

    public Iterable<MovieWithLink> getAllMovies() {
        Iterable<Movie> movies = movieRepository.findAll();
        return MovieDto(movies);
    }

    public Iterable<Movie> saveAll(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }
}
