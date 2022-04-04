package com.gorshkov.movieland.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long movieId;
    private String nameNative;
    private String nameRussian;
    private String yearOfRelease;
    private double rating;
    private double price;
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "movie_countries",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "country_id")})
    @JsonIgnore
    private List<Country> countries;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "movie_genres",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    @JsonIgnore
    private List<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Movie movie = (Movie) o;
        return movieId != null && Objects.equals(movieId, movie.movieId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "movieId = " + movieId + ", " +
                "nameNative = " + nameNative + ", " +
                "nameRussian = " + nameRussian + ", " +
                "yearOfRelease = " + yearOfRelease + ", " +
                "rating = " + rating + ", " +
                "price = " + price + ", " +
                "description = " + description + ")";
    }
}
