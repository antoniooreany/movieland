package com.gorshkov.movieland.repository;

import com.gorshkov.movieland.model.Poster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends CrudRepository<Poster, Long> {
}
