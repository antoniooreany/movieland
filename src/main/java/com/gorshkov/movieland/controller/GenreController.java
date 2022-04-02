package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.Genre;
import com.gorshkov.movieland.service.GenreService;
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
@RequestMapping(path = "/genres")
public class GenreController {

    private static final String URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e09/download/genre.txt";

    private final GenreService genreService;

    @GetMapping("/add")
    public String addGenre() {

        List<String> rows = getRowsFromUrl(URL_STRING);
        List<Genre> genres = new ArrayList<>();

        for (String row : rows) {
            Genre genre = new Genre();
            genre.setGenre(row);
            genres.add(genre);
            log.info("user: {}", genre);
            genreService.addGenre(genre);
        }
        return "redirect:/users";
    }

    @GetMapping()
    public Iterable<Genre> getAll(Model model) {

        Iterable<Genre> genres = genreService.getAllGenres();
        model.addAttribute("genre", genres);
        return genres;
    }
}