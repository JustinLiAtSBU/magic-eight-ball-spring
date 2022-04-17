package com.example.magic_eight_ball.controller;

import com.example.magic_eight_ball.model.TvShow;
import com.example.magic_eight_ball.repository.tvshow.TvShowRepository;
import com.example.magic_eight_ball.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/tvshows")
public class TvShowController {

    @Autowired
    TvShowRepository tvShowRepository;

    @Autowired
    TvShowService tvShowService;

    @GetMapping("/all")
    public ResponseEntity<List<TvShow>> getAllTvShows(
            @RequestParam(required = false) Integer top,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Integer minVotes,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) List<String> genres
    ) {
        try {
            List<TvShow> tvShows = tvShowService.getTvShowsByCriteria(top, country, minRating, minVotes, minYear, genres);
            if (tvShows.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tvShows, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{tconst}")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable("tconst") String tconst) {
        System.out.println("tconst: " + tconst);
        Optional<TvShow> tvShowData = tvShowRepository.findByTconst(tconst);
        return tvShowData.map(tvShow -> new ResponseEntity<>(tvShow, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/random")
    public ResponseEntity<TvShow> getRandomTvShow(
            @RequestParam(required = false) Integer top,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Integer minVotes,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) List<String> genres
    ) {
        try {
            TvShow randomTvShow = tvShowService.getRandomTvShow(top, country, minRating, minVotes, minYear, genres);
            if (randomTvShow == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(randomTvShow, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
