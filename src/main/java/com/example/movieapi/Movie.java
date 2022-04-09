package com.example.movieapi;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String genre;
    private double rate;
    private String description;
    private int rateNum;

    public Movie(){
    }

    public Movie(int identifier, String filmTitle) {
        id = identifier;
        title = filmTitle;
    }

    // ID Getter/Setter
    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Title Getter/Setter
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String filmTitle) {
        title = filmTitle;
    }

    // Genre Getter/Setter
    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }
    public void setGenre(String filmGenre) {
        genre = filmGenre;
    }

    // Rate Getter/Setter
    @Basic
    @Column(name = "rate")
    public double getRate() {
        return rate;
    }
    public void setRate(double filmRating) {
        rate = filmRating;
    }

    // Description Getter/Setter
    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String filmDescription) {
        description = filmDescription;
    }

    // RateNum Getter/Setter
    @Basic
    @Column(name = "rateNum")
    public int getRateNum() {
        return rateNum;
    }
    public void setRateNum(int filmRateNum) {
        rateNum = filmRateNum;
    }

    @Override
    public boolean equals(Object o) {
        // if same movie,
        if (this == o) return true;
        // if null movie or if movie isn't movie
        if (o == null || getClass() != o.getClass()) return false;

        // compare movie id and title
        Movie comparedMovie = (Movie) o;
        return id == comparedMovie.id && Objects.equals(title, comparedMovie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
