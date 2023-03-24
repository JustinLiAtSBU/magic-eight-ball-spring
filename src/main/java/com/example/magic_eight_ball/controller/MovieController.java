package com.example.magic_eight_ball.controller;

import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.repository.movie.MovieRepository;
import com.example.magic_eight_ball.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies(
            @RequestParam(required = false) Integer top,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Integer minVotes,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) List<String> genres
    ) {
        try {
            List<Movie> movies = movieService.getMoviesByCriteria(top, country, minRating, minVotes, minYear, genres);
            if (movies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getMovieCount() {
        try {
            List<Movie> movies = movieRepository.findAll();
            return new ResponseEntity<>(movies.size(), HttpStatus.OK);
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

    @GetMapping("/random")
    public ResponseEntity<Movie> getRandomMovie(
            @RequestParam(required = false) Integer top,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Integer minVotes,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) List<String> genres
    ) {
        try {
            Movie randomMovie = movieService.getRandomMovie(top, country, minRating, minVotes, minYear, genres);
            if (randomMovie == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(randomMovie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/random/{channelId}")
    public ResponseEntity<Movie> getNonWatchedRandomMovie(
            @PathVariable("channelId") String channelId,
            @RequestParam(required = false) Integer top,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Integer minVotes,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) List<String> genres
    ) {
        try {
            Movie randomMovie = movieService.getChannelsNonWatchedRandomMovie(channelId, top, country, minRating, minVotes, minYear, genres);
            if (randomMovie == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(randomMovie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
