package com.example.magic_eight_ball.controller;

import com.example.magic_eight_ball.model.TvShow;
import com.example.magic_eight_ball.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/tvshows")
public class TvShowController {

    @Autowired
    TvShowRepository tvShowRepository;

    @GetMapping("/all")
    public ResponseEntity<List<TvShow>> getAllTvShows() {
        try {
            List<TvShow> tvShows = new ArrayList<>(tvShowRepository.findAll());
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

    @PostMapping("/post")
    public ResponseEntity<TvShow> createTvShow(@RequestBody TvShow tvShow) {
        try {
            System.out.println(tvShow.toString());
            tvShowRepository.save(tvShow);
            return new ResponseEntity<>(tvShow, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
