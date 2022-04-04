package com.gorshkov.movieland.util;

import com.gorshkov.movieland.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.gorshkov.movieland.util.FileReaderUtil.getRowsFromUrl;

@Slf4j
public class Parser {

    private static final String REVIEW_URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0f/download/review.txt";
    private static final String USER_URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e11/download/user.txt";
    private static final String POSTER_URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0d/download/poster.txt";
    private static final String MOVIE_URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0b/download/movie.txt";
    private static final String GENRE_URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e09/download/genre.txt";
    private static final int REVIEW_LINES_NUMBER = 3;
    private static final int USER_LINES_NUMBER = 3;
    private static final int MOVIE_LINES_NUMBER = 7;

    public static List<Review> parseReview() {
        List<String> rows = getRowsFromUrl(REVIEW_URL_STRING);
        List<Review> reviews = new ArrayList<>();
        Review review = null;

        for (int currentRowNumber = 0; currentRowNumber < rows.size(); currentRowNumber++) {
            if (currentRowNumber % REVIEW_LINES_NUMBER == 0) {
                review = new Review();
                Movie movie = new Movie();
                movie.setNameNative(rows.get(currentRowNumber));
                review.setMovie(movie);
            } else if (currentRowNumber % REVIEW_LINES_NUMBER == 1) {
                User user = new User();
                user.setNickname(rows.get(currentRowNumber));
            } else {
                review.setText(rows.get(currentRowNumber));
                reviews.add(review);
            }
        }
        return reviews;
    }

    public static List<User> parseUser() {
        List<String> rows = getRowsFromUrl(USER_URL_STRING);
        List<User> users = new ArrayList<>();
        User user = null;

        for (int currentRowNumber = 0; currentRowNumber < rows.size(); currentRowNumber++) {
            if (currentRowNumber % USER_LINES_NUMBER == 0) {
                user = new User();
                user.setNickname(rows.get(currentRowNumber));
            } else if (currentRowNumber % USER_LINES_NUMBER == 1) {
                user.setEmail(rows.get(currentRowNumber));
            } else {
                user.setPassword(rows.get(currentRowNumber));
                users.add(user);
            }
        }
        return users;
    }

    public static List<Poster> parsePoster() {
        List<String> rows = getRowsFromUrl(POSTER_URL_STRING);
        List<Poster> posters = new ArrayList<>();

        for (String row : rows) {
            String[] split = row.split(" https:");
            Poster poster = new Poster();
            poster.setNameRussian(split[0]);
            poster.setPicturePath("https:" + split[1]);
            posters.add(poster);
        }
        return posters;
    }

    public static List<Movie> parseMovie() {
        List<String> rows = getRowsFromUrl(MOVIE_URL_STRING);
        List<Movie> movies = new ArrayList<>();
        Movie movie = null;

        for (int currentRowNumber = 0; currentRowNumber < rows.size(); currentRowNumber++) {
            if (currentRowNumber % MOVIE_LINES_NUMBER == 0) {
                movie = new Movie();
                String[] split = rows.get(currentRowNumber).split("/");
                movie.setNameRussian(split[0]);
                movie.setNameNative(split[1]);
            } else if (currentRowNumber % MOVIE_LINES_NUMBER == 1) {
                movie.setYearOfRelease(rows.get(currentRowNumber));
            } else if (currentRowNumber % MOVIE_LINES_NUMBER == 2) {
                Country country = new Country();
                String s = rows.get(currentRowNumber);
                List<Country> countries = new ArrayList<>();
                country.setName(s);
                countries.add(country);
            } else if (currentRowNumber % MOVIE_LINES_NUMBER == 3) {
                String[] genreNames = rows.get(currentRowNumber).split(", ");
                List<Genre> genres = new ArrayList<>();
                for (String genreName : genreNames) {
                    Genre genre = new Genre();
                    genre.setName(genreName);
                    genres.add(genre);
                }
                movie.setGenres(genres);
            } else if (currentRowNumber % MOVIE_LINES_NUMBER == 4) {
                movie.setDescription(rows.get(currentRowNumber));
            } else if (currentRowNumber % MOVIE_LINES_NUMBER == 5) {
                String row = rows.get(currentRowNumber).split(":")[1];
                movie.setRating(Double.parseDouble(row));
            } else {
                String row = rows.get(currentRowNumber).split(":")[1];
                movie.setPrice(Double.parseDouble(row));
                movies.add(movie);
            }
        }
        return movies;
    }

    public static Iterable<Genre> parseGenre() {
        List<String> rows = getRowsFromUrl(GENRE_URL_STRING);
        List<Genre> genres = new ArrayList<>();

        for (String row : rows) {
            Genre genre = new Genre();
            genre.setName(row);
            genres.add(genre);
        }
        return genres;
    }
}
