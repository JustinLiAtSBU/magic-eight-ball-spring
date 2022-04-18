package com.example.magic_eight_ball.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class User {

    @Id
    private String id;

    private String name;
    private LinkedHashMap<String, Movie> watchedMovies;
    private LinkedHashMap<String, TvShow> watchedTvShows;

    public User(String name) {
        this.name = name;
        this.watchedMovies= new LinkedHashMap<>();
        this.watchedTvShows = new LinkedHashMap<>();
    }

    public User(String name, Movie movie) {
        this.name = name;
        LinkedHashMap<String, Movie> movies = new LinkedHashMap<>();
        movies.put(movie.getTconst(), movie);
        this.watchedMovies = movies;
        this.watchedTvShows = new LinkedHashMap<>();
    }
}
