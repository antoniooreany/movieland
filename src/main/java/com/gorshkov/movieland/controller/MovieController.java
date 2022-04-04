package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.Movie;
import com.gorshkov.movieland.model.dto.MovieWithLink;
import com.gorshkov.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gorshkov.movieland.util.Parser.parseMovie;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/add")
    public Iterable<Movie> addMovies() {
        List<Movie> movies = parseMovie();
        Iterable<Movie> movieIterable = movieService.saveAll(movies);
        log.info("addMovies to DB {}", movieIterable);
        return movieIterable;
    }

    @GetMapping()
    public Iterable<MovieWithLink> getAll(Model model) {
        Iterable<MovieWithLink> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        log.info("Get All Movies {}", movies);
        return movies;
    }

    @GetMapping("/random")
    public Iterable<MovieWithLink> getThreeRandom(Model model) {
        Iterable<MovieWithLink> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        log.info("Get All Movies {}", movies);
        return movies;
    }
}
