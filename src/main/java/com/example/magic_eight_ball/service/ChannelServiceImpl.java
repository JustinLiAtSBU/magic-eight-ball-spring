package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.Channel;
import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.model.TvShow;
import com.example.magic_eight_ball.model.User;
import com.example.magic_eight_ball.repository.channel.ChannelRepository;
import com.example.magic_eight_ball.repository.movie.MovieRepository;
import com.example.magic_eight_ball.repository.tvshow.TvShowRepository;
import com.example.magic_eight_ball.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TvShowRepository tvShowRepository;

    @Override
    public Channel createChannelWithMembers(String channelId, String name, HashSet<String> members) {
        for (String member: members) {
            if (!userRepository.findByName(member).isPresent()) {
                System.out.println("Creating new user: " + member);
                User newUser = new User(member);
                userRepository.save(newUser);
            }
        }
        Channel channel = new Channel(channelId, name, members);
        channelRepository.save(channel);
        return channel;
    }

    @Override
    public Channel updateChannelMembers(Channel channel, HashSet<String> members) {
        System.out.println("Inside updateChannelMembers");
        for (String member: members) {
            if (!userRepository.findByName(member).isPresent()) {
                System.out.println("Creating new user: " + member);
                User newUser = new User(member);
                userRepository.save(newUser);
            }
        }
        channel.setMembers(members);
        channelRepository.save(channel);
        return channel;
    }

    @Override
    public Channel updateChannelWatchedMovies(Channel channel, Movie movie) {
        if (movieRepository.findByTconst(movie.getTconst()).isPresent()) {
            channel.getWatchedMovies().add(movie.getTconst());
            channelRepository.save(channel);
        }
        return channel;
    }

    @Override
    public Channel updateChannelWatchedTvShows(Channel channel, TvShow tvShow) {
        if (tvShowRepository.findByTconst(tvShow.getTconst()).isPresent()) {
            channel.getWatchedTvShows().add(tvShow.getTconst());
            channelRepository.save(channel);
        }
        return channel;
    }
}
