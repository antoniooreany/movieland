package com.gorshkov.movieland.service;

import com.gorshkov.movieland.model.Genre;
import com.gorshkov.movieland.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Iterable<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}