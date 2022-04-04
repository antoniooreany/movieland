package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.Genre;
import com.gorshkov.movieland.model.dto.GenreWithoutMovie;
import com.gorshkov.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gorshkov.movieland.util.Parser.parseGenre;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre/add")
    public Iterable<Genre> addGenres() {
        Iterable<Genre> genres = parseGenre();
        return genreService.saveAll(genres);
    }

    @GetMapping("/genre")
    public Iterable<GenreWithoutMovie> getAll(Model model) {
        Iterable<GenreWithoutMovie> genres = genreService.getAll();
        model.addAttribute("genre", genres);
        return genres;
    }

    @GetMapping("/random/genre/{genreId}")
    public Genre getById(
            @PathVariable(value = "genreId") Long id) {
        return genreService.getById(id);
    }
}