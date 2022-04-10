package com.example.movieapi;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "genre")
    private String genre;

    @Basic
    @Column(name = "rate")
    private double rate;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "rateNum")
    private int rateNum;

    public Movie(){
    }

    public Movie(int identifier, String filmTitle) {
        id = identifier;
        title = filmTitle;
    }

    // ID Getter/Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Title Getter/Setter
    public String getTitle() {
        return title;
    }
    public void setTitle(String filmTitle) {
        title = filmTitle;
    }

    // Genre Getter/Setter
    public String getGenre() {
        return genre;
    }
    public void setGenre(String filmGenre) {
        genre = filmGenre;
    }

    // Rate Getter/Setter
    public double getRate() {
        return rate;
    }
    public void setRate(double filmRating) {
        rate = filmRating;
    }

    // Description Getter/Setter
    public String getDescription() {
        return description;
    }
    public void setDescription(String filmDescription) {
        description = filmDescription;
    }

    // RateNum Getter/Setter
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

    @Override
    public String toString(){
        return "ID: " + id + "\n Title: " + title + "\n Genre " + genre + "\n Rate: " + rate
                + "\n Description: " + description + "\n RateNum: " + rateNum;
    }
}
