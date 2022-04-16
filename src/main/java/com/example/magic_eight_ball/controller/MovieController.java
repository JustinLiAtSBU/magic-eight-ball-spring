package com.example.magic_eight_ball.controller;

import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.magic_eight_ball.utils.CountryMap;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    CountryMap countryMap = new CountryMap();

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        try {
            List<Movie> movies = new ArrayList<>(movieRepository.findAll());
            if (movies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{tconst}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("tconst") String tconst) {
        System.out.println("tconst: " + tconst);
        Optional<Movie> movieData = movieRepository.findByTconst(tconst);
        return movieData.map(movie -> new ResponseEntity<>(movie, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
