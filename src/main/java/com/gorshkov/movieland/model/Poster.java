package com.gorshkov.movieland.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "poster")
public class Poster {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long posterId;
    private String picturePath;
    private String nameRussian;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Poster poster = (Poster) o;
        return posterId != null && Objects.equals(posterId, poster.posterId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Poster{" +
                "posterId=" + posterId +
                ", picturePath='" + picturePath + '\'' +
                ", nameRussian='" + nameRussian + '\'' +
                '}';
    }
}
