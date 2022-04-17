package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.repository.CustomMovieRepository;
import com.example.magic_eight_ball.repository.MovieRepository;
import com.example.magic_eight_ball.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CustomMovieRepository customMovieRepository;


    @Override
    public List<Movie> getMoviesByCriteria(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        Sort sort = Sort.by(Sort.Order.desc("rating"));
        Query query = QueryBuilder.motionPictureQuery(top, iso, minRating, minVotes, minYear, genres, sort);
        return customMovieRepository.getMoviesByFields(query);
    }

    @Override
    public Movie getRandomMovie(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        List<Movie> movies = getMoviesByCriteria(top, iso, minRating, minVotes, minYear, genres);
        int upperBound = movies.size();
        if (top != null && top <= movies.size()) {
            upperBound = top;
        }
        Random rand = new Random();
        int randomNum = rand.nextInt(upperBound);
        return movies.get(randomNum);
    }
}
