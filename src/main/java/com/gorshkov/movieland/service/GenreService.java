package com.gorshkov.movieland.service;

import com.gorshkov.movieland.model.Genre;
import com.gorshkov.movieland.model.dto.GenreWithoutMovie;
import com.gorshkov.movieland.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.gorshkov.movieland.util.dtoConvector.GenreDto;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre add(Genre genre) {
        return genreRepository.save(genre);
    }

    public Iterable<GenreWithoutMovie> getAll() {
        Iterable<Genre> genres = genreRepository.findAll();
        return GenreDto(genres);
    }

    public Iterable<Genre> saveAll(Iterable<Genre> genres) {
        return genreRepository.saveAll(genres);
    }

    public Genre getById(Long id) {
        return genreRepository.findById(id).orElseThrow();
    }
}