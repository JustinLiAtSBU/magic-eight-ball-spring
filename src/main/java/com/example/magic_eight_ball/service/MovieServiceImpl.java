package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.Channel;
import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.repository.channel.ChannelRepository;
import com.example.magic_eight_ball.repository.movie.CustomMovieRepository;
import com.example.magic_eight_ball.repository.movie.MovieRepository;
import com.example.magic_eight_ball.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CustomMovieRepository customMovieRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Override
    public List<Movie> getMoviesByCriteria(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        Sort sort = Sort.by(Sort.Order.desc("rating"));
        Query query = QueryBuilder.motionPictureQuery(top, iso, minRating, minVotes, minYear, genres, sort);
        return customMovieRepository.getMoviesByFields(query);
    }

    @Override
    public Movie getRandomMovie(Integer top, String country, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        List<Movie> movies = getMoviesByCriteria(top, country, minRating, minVotes, minYear, genres);
        int upperBound = movies.size();
        if (top != null && top <= movies.size()) {
            upperBound = top;
        }
        Random rand = new Random();
        int randomNum = rand.nextInt(upperBound);
        return movies.get(randomNum);
    }

    @Override
    public Movie getChannelsNonWatchedRandomMovie(String channelId, Integer top, String country, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        Optional<Channel> channel = channelRepository.findByChannelId(channelId);
        if (channel.isPresent()) {
            Channel confirmedChannel = channel.get();
            List<Movie> movies = getMoviesByCriteria(top, country, minRating, minVotes, minYear, genres);
            for (String tconst: confirmedChannel.getWatchedMovies()) {
                movies.removeIf(movie -> movie.getTconst().equals(tconst));
            }
            int upperBound = movies.size();
            if (top != null && top <= movies.size()) {
                upperBound = top;
            }
            Random rand = new Random();
            int randomNum = rand.nextInt(upperBound);
            return movies.get(randomNum);
        } else {
            return getRandomMovie(top, country, minRating, minVotes, minYear, genres);
        }
    }
}
