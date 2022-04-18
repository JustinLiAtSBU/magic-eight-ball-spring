package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.Channel;
import com.example.magic_eight_ball.model.TvShow;
import com.example.magic_eight_ball.model.TvShow;
import com.example.magic_eight_ball.repository.channel.ChannelRepository;
import com.example.magic_eight_ball.repository.tvshow.CustomTvShowRepository;
import com.example.magic_eight_ball.repository.tvshow.TvShowRepository;
import com.example.magic_eight_ball.utils.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TvShowServiceImpl implements TvShowService {

    @Autowired
    TvShowRepository tvShowRepository;

    @Autowired
    CustomTvShowRepository customTvShowRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Override
    public List<TvShow> getTvShowsByCriteria(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        Sort sort = Sort.by(Sort.Order.desc("rating"));
        Query query = QueryBuilder.motionPictureQuery(top, iso, minRating, minVotes, minYear, genres, sort);
        return customTvShowRepository.getTvShowsByFields(query);
    }

    @Override
    public TvShow getRandomTvShow(Integer top, String iso, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        List<TvShow> tvShows = getTvShowsByCriteria(top, iso, minRating, minVotes, minYear, genres);
        int upperBound = tvShows.size();
        if (top != null && top <= tvShows.size()) {
            upperBound = top;
        }
        Random rand = new Random();
        int randomNum = rand.nextInt(upperBound);
        return tvShows.get(randomNum);
    }

    @Override
    public TvShow getChannelsNonWatchedRandomTvShow(String channelId, Integer top, String country, Double minRating, Integer minVotes, Integer minYear, List<String> genres) {
        Optional<Channel> channel = channelRepository.findByChannelId(channelId);
        if (channel.isPresent()) {
            Channel confirmedChannel = channel.get();
            List<TvShow> tvShows = getTvShowsByCriteria(top, country, minRating, minVotes, minYear, genres);
            for (String tconst: confirmedChannel.getWatchedTvShows()) {
                tvShows.removeIf(tvShow -> tvShow.getTconst().equals(tconst));
            }
            int upperBound = tvShows.size();
            if (top != null && top <= tvShows.size()) {
                upperBound = top;
            }
            Random rand = new Random();
            int randomNum = rand.nextInt(upperBound);
            return tvShows.get(randomNum);
        } else {
            return getRandomTvShow(top, country, minRating, minVotes, minYear, genres);
        }
    }
}
