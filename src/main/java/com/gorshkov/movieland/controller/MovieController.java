package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.Genre;
import com.gorshkov.movieland.model.Movie;
import com.gorshkov.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.gorshkov.movieland.util.FileReaderUtil.getRowsFromUrl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/movies")
public class MovieController {

    private static final String URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0b/download/movie.txt";
    private static final int LINES_NUMBER = 7;

    private final MovieService movieService;

    @GetMapping("/add")
    public String addMovie() {

        List<String> rows = getRowsFromUrl(URL_STRING);
        List<Movie> movies = new ArrayList<>();
        Movie movie = null;

        for (int currentRowNumber = 0; currentRowNumber < rows.size(); currentRowNumber++) {
            if (currentRowNumber % LINES_NUMBER == 0) {
                movie = new Movie();
                movie.setMovieName(rows.get(currentRowNumber));
            } else if (currentRowNumber % LINES_NUMBER == 1) {
                movie.setYear(Integer.parseInt(rows.get(currentRowNumber)));
            } else if (currentRowNumber % LINES_NUMBER == 2) {
                movie.setCounty(rows.get(currentRowNumber));
            } else if (currentRowNumber % LINES_NUMBER == 3) {
                String[] genresString = rows.get(currentRowNumber).split(" ,");
                Set<Genre> genres = new HashSet<>();
                Genre genre = new Genre();
                for (String genreString : genresString) {
                    genre.setGenre(genreString);
                    genres.add(genre);
                }
                movie.setGenres(genres);
            } else if (currentRowNumber % LINES_NUMBER == 4) {
                movie.setDescription(rows.get(currentRowNumber));
            } else if (currentRowNumber % LINES_NUMBER == 5) {
                String row = rows.get(currentRowNumber).split(":")[1];
                movie.setRating(Double.parseDouble(row));
            } else {
                String row = rows.get(currentRowNumber).split(":")[1];
                movie.setPrice(Double.parseDouble(row));
                movies.add(movie);
                log.info("movie: {}", movie);
                movieService.addMovie(movie);
            }
        }
        return "redirect:/users";
    }

    @GetMapping()
    public Iterable<Movie> getAll(Model model) {

        Iterable<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return movies;
    }
}
