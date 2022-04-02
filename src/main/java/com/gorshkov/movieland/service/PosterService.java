package com.gorshkov.movieland.service;

import com.gorshkov.movieland.model.Poster;
import com.gorshkov.movieland.repository.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosterService {

    private final PosterRepository posterRepository;

    public Poster addPoster(Poster poster) {
        return posterRepository.save(poster);
    }

    public Iterable<Poster> getAllPosters() {
        return posterRepository.findAll();
    }

}
