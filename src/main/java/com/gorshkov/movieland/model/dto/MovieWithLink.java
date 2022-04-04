package com.gorshkov.movieland.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieWithLink {
    private Long movieId;
    private String nameNative;
    private String nameRussian;
    private String yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;
}
