package com.example.magic_eight_ball.service;

import com.example.magic_eight_ball.model.Channel;
import com.example.magic_eight_ball.model.Movie;
import com.example.magic_eight_ball.model.TvShow;

import java.util.HashSet;

public interface ChannelService {
    Channel createChannelWithMembers(String channelId, String name, String guild, HashSet<String> members);
    Channel updateChannelMembers(Channel channel, HashSet<String> members);
    Channel updateChannelWatchedMovies(Channel channel, Movie movie);
    Channel updateChannelWatchedTvShows(Channel confirmedChannel, TvShow tvShow);
}
