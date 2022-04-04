package com.gorshkov.movieland.model.dto;

import lombok.Data;

@Data
public class GenreWithoutMovie {
    private Long genreId;
    private String name;
}
